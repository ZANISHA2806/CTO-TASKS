package repository;

import sample.SampleData;
import enums.AccountStatus;

public class AccountRepository {

    public double getBalance(String vpa) {
        return SampleData.getBalance(vpa);
    }

    public AccountStatus getStatus(String vpa) {
        return SampleData.getAccountStatus(vpa);
    }

    public boolean exists(String vpa) {
        return SampleData.isAccountPresent(vpa);
    }

    public void debit(String vpa, double amount) {
        double balance = SampleData.getBalance(vpa);
        SampleData.updateBalance(vpa, balance - amount);
    }
}