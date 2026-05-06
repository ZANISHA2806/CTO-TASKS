package model;

import enums.AccountStatus;

public class Account {

    private String vpa;
    private double balance;
    private AccountStatus status;

    public Account(String vpa, double balance, AccountStatus status) {
        this.vpa = vpa;
        this.balance = balance;
        this.status = status;
    }

    public String getVpa() {
        return vpa;
    }

    public double getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }
}