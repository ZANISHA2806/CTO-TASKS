package payment;

import java.time.*;
import java.util.*;

abstract class payments {
    public abstract boolean verifypayment();
}

class creditcard extends payments {

    String cardnumber;
    String cvv;
    int month, year;

    creditcard(String cardnumber, String cvv, int month, int year) {
        this.cardnumber = cardnumber;
        this.cvv = cvv;
        this.month = month;
        this.year = year;
    }

    public boolean isvalid() {
        boolean shouldDouble = false;
        int sum = 0;

        for (int i = cardnumber.length() - 1; i >= 0; i--) {
            int digit = cardnumber.charAt(i) - '0';

            if (shouldDouble) {
                digit = digit * 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            shouldDouble = !shouldDouble;
        }

        return (sum % 10 == 0);
    }

    public boolean isexpired() {
        YearMonth current = YearMonth.now();
        YearMonth expiry = YearMonth.of(year, month);

        return expiry.isAfter(current) || expiry.equals(current);
    }

    public boolean isvalidcvv() {
        return cvv.matches("\\d{3,4}");
    }

    @Override
    public boolean verifypayment() {
        return isvalid() && isexpired() && isvalidcvv();
    }
}

class digitalpayment extends payments {

    String mobile;
    String pin;
    String actualpin;
    double withdraw;
    double amount;

    digitalpayment(String mobile, String pin, String actualpin, double withdraw, double amount) {
        this.mobile = mobile;
        this.pin = pin;
        this.actualpin = actualpin;
        this.withdraw = withdraw;
        this.amount = amount;
    }

    public boolean mobilevalid() {
        return mobile.matches("\\d{10}");
    }

    public boolean pinvalid() {
        return pin.matches("\\d{4,6}");
    }

    public boolean pinmatch() {
        return pin.equals(actualpin);
    }

    public boolean sufficientbalance() {
        return amount >= withdraw;
    }

    @Override
    public boolean verifypayment() {
        return mobilevalid() && pinvalid() && pinmatch() && sufficientbalance();
    }
}



public class Payment_verification {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        List<payments> list = new ArrayList<>();
        System.out.println("Enter number of payments:");
        int n=scan.nextInt();
        scan.nextLine();
        
        for (int i = 0; i < n; i++) {

        System.out.println("Choose payment type: 1.Credit card  2.Digital payment");
        int choice = scan.nextInt();
        scan.nextLine();

        payments p;

        if (choice == 1) {
            System.out.println("Enter card number:");
            String card = scan.nextLine();

            System.out.println("Enter cvv:");
            String cvv = scan.nextLine();

            System.out.println("Enter expiry month:");
            int month = scan.nextInt();

            System.out.println("Enter expiry year:");
            int year = scan.nextInt();

            p = new creditcard(card, cvv, month, year);

        } else {
            System.out.println("Enter mobile number:");
            String mobile = scan.nextLine();

            System.out.println("Enter PIN:");
            String pin = scan.nextLine();

            System.out.println("Enter actual PIN:");
            String actualPin = scan.nextLine();

            System.out.println("Enter amount:");
            double amount = scan.nextDouble();

            System.out.println("Enter withdraw amount:");
            double withdraw = scan.nextDouble();

            p = new digitalpayment(mobile, pin, actualPin, withdraw, amount);
        }
        list.add(p); 
        }
        for (payments p : list) {
       
        if (p.verifypayment()) {
            System.out.println("Payment Successful");
        } else {
            System.out.println("Payment Failed");
        }
        }

        scan.close();
    }
}