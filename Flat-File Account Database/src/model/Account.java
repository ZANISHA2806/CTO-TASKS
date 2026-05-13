package model;

import enums.*;

public class Account {

    private String accId;
    private String holdername;
    private double balance;
    private Status status;
    private AccountType acctype;

    public Account(
            String accId,
            String holdername,
            double balance,
            AccountType acctype,
            Status status
    ) {

        this.accId = accId;
        this.holdername = holdername;
        this.acctype = acctype;
        this.status = status;
        this.balance = balance;
    }

    public String getAccId() {
        return accId;
    }

    public String getHoldername() {
        return holdername;
    }

    public Status getStatus() {
        return status;
    }

    public AccountType getAccType() {
        return acctype;
    }

    public double getBalance() {
        return balance;
    }

    public String toFileRecord() {

        return accId + "|" +
               holdername + "|" +
               balance + "|" +
               acctype + "|" +
               status;
    }

    public static Account fromFileRecord(String line) {

        String[] parts = line.split("\\|");

        String accId = parts[0];
        String holdername = parts[1];

        double balance =
                Double.parseDouble(parts[2]);

        AccountType acctype =
                AccountType.valueOf(parts[3]);

        Status status =
                Status.valueOf(parts[4]);

        return new Account(
                accId,
                holdername,
                balance,
                acctype,
                status
        );
    }

    @Override
    public String toString() {
        return toFileRecord();
    }
}