package fraud_monitor;

import java.sql.*;
import java.util.Scanner;

class TransactionLimitException extends Exception {
    public TransactionLimitException(String msg) {
        super(msg);
    }
}

public class Fraud_Monitor {

    static final String URL = "jdbc:mysql://localhost:3306/TRANSACTION";
    static final String USER = "root";
    static final String PASS = "Rahamath@2005";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            while (true) {

                System.out.println("1. TRANSACTION");
                System.out.println("2. EXIT");
                System.out.print("Enter choice: ");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {

                    case 1: {

                        System.out.println("Enter user id:");
                        String userId = scan.nextLine();

                        if (isFlagged(conn, userId)) {
                            System.out.println("BLOCKED: user already flagged");
                            break;
                        }

                        try {
                            int count = getRecentTransactionCount(conn, userId);

                            if (count >= 3) {
                                flagUser(conn, userId);
                                throw new TransactionLimitException("Limit exceeded for " + userId);
                            }

                            insertTransaction(conn, userId);
                            System.out.println("Transaction successful");

                        } catch (TransactionLimitException e) {
                            System.out.println("USER FLAGGED: " + userId);
                        }

                        break;
                    }

                    case 2: {
                        System.out.println("Exiting system");
                        showFlaggedUsers(conn);
                        scan.close();
                        return;
                    }

                    default:
                        System.out.println("Invalid choice");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertTransaction(Connection conn, String userId) throws SQLException {
        String sql = "INSERT INTO transactions(user_id) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        }
    }

    static int getRecentTransactionCount(Connection conn, String userId) throws SQLException {

        String sql = """
            SELECT COUNT(*) 
            FROM transactions 
            WHERE user_id = ? 
            AND txn_time >= NOW() - INTERVAL 3 MINUTE
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    static void flagUser(Connection conn, String userId) throws SQLException {
        String sql = "INSERT INTO flagged_users(user_id) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        }
    }

    static boolean isFlagged(Connection conn, String userId) throws SQLException {
        String sql = "SELECT user_id FROM flagged_users WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    static void showFlaggedUsers(Connection conn) throws SQLException {

        System.out.println("FLAGGED USERS");

        String sql = "SELECT * FROM flagged_users";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(rs.getString("user_id") + " " +
                        rs.getTimestamp("flagged_time"));
            }

            if (!found) {
                System.out.println("No flagged users");
            }
        }
    }
}