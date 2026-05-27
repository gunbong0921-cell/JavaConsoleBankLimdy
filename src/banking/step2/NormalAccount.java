package banking.step2;

import banking.step2.*;

public class NormalAccount extends Account {
    private int interestRate; 

    public NormalAccount(String accountId, String customerName, int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(int money) {
        int totalDeposit = money + (int)(money * (interestRate / 100.0));
        super.deposit(totalDeposit);
    }

    @Override
    public void showAccountInfo() {
        super.showAccountInfo();
        System.out.println("기본이자>" + interestRate + "%");
    }
    public int getInterestRate() {
        return interestRate;
    }
}
