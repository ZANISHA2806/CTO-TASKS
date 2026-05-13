package main;
import exception.DuplicateKeyException;
import java.util.List;
import java.util.Scanner;

import model.Account;
import enums.AccountType;
import enums.Status;
import service.AccService;
import util.LoggerUtil;
import enums.UpdateType;
public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        AccService service =
                new AccService();

        int choice;

        do {

            System.out.println(
                    "\n ACCOUNT DATABASE SYSTEM "
            );

            System.out.println(
                    "1. Create Account"
            );

            System.out.println(
                    "2. Read Account"
            );

            System.out.println(
                    "3. Update Account"
            );

            System.out.println(
                    "4. Delete Account"
            );

            System.out.println(
                    "5. List All Accounts"
            );

            System.out.println(
                    "6. Exit"
            );

            System.out.print(
                    "Enter your choice: "
            );

            choice =
                    scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                  case 1:

                    System.out.print(
                            "Enter Account ID: "
                    );

                    String accountId =
                            scanner.nextLine();

                    System.out.print(
                            "Enter Holder Name: "
                    );

                    String holderName =
                            scanner.nextLine();

                    System.out.print(
                            "Enter Balance: "
                    );

                    double balance =
                            scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print(
                            "Enter Account Type (SAVINGS/CURRENT): "
                    );

                    AccountType accountType =
                            AccountType.valueOf(
                                    scanner.nextLine()
                                            .toUpperCase()
                            );

                    System.out.print(
                            "Enter Status (ACTIVE/INACTIVE/BLOCKED): "
                    );

                    Status status =
                            Status.valueOf(
                                    scanner.nextLine()
                                            .toUpperCase()
                            );

                    Account account =
                            new Account(
                                    accountId,
                                    holderName,
                                    balance,
                                    accountType,
                                    status
                            );

                    try {

                        service.create(account);

                        System.out.println(
                                "Account Created Successfully"
                        );

                        System.out.println(account);

                    } catch (DuplicateKeyException e) {

                        LoggerUtil.error(
                                e.getMessage()
                        );
                    }

                    break;

                case 2:

                    System.out.print(
                            "Enter Account ID: "
                    );

                    String readId =
                            scanner.nextLine();

                    Account found =
                            service.read(readId);

                    if (found != null) {

                        System.out.println(
                                "Account Found:"
                        );

                        System.out.println(found);

                    } else {

                        System.out.println(
                                "Account Not Found"
                        );
                    }

                    break;
                    
                case 3:
                    System.out.println("1. Update Name");
                    System.out.println("2. Update Type");
                    System.out.println("3. Credit");
                    System.out.println("4. Debit");

                    int choice1 = scanner.nextInt();

                    System.out.println("Enter Account ID:");
                    String id = scanner.next();

                    switch (choice1) {

                    case 1:
                        System.out.println("Enter new name:");
                        String name = scanner.next();
                        service.update(id, UpdateType.HOLDERNAME, name);
                        break;

                    case 2:
                        System.out.println("Enter account type (SAVINGS/CURRENT):");
                        String type = scanner.next();
                        service.update(id, UpdateType.ACCTYPE, type);
                        break;

                    case 3:
                        System.out.println("Enter credit amount:");
                        String credit = scanner.next();
                        service.update(id, UpdateType.CREDIT, credit);
                        break;

                    case 4:
                        System.out.println("Enter debit amount:");
                        String debit = scanner.next();
                        service.update(id, UpdateType.DEBIT, debit);
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
                    break;
//
                case 4:

                    System.out.print(
                            "Enter Account ID: "
                    );
                    

                    String deleteId =
                            scanner.nextLine();
                    System.out.print(
                            "Enter Account holdername: "
                    );
                    

                    String deletename =
                            scanner.nextLine();
                    

                    System.out.print(
                            "Are you sure to delete? (yes/no): "
                    );

                    String confirm =
                            scanner.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {

                        service.delete(deleteId,deletename);

                    } else {

                        System.out.println(
                                "Delete Cancelled"
                        );
                    }

                    break;

              case 5:

                    List<Account> accounts =
                            service.listAll();

                    if (accounts.isEmpty()) {

                        System.out.println(
                                "No accounts found"
                        );

                    } else {

                        System.out.println(
                                "\n ALL ACCOUNTS "
                        );

                        for (Account acc : accounts) {

                            System.out.println(acc);

                            LoggerUtil.info(
                                    "Account Record: " + acc
                            );
                        }
                    }

                    break;

                case 6:

                    System.out.println(
                            "Exiting Application..."
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid Choice"
                    );
            }

        } while (choice != 6);

        scanner.close();
    }
}