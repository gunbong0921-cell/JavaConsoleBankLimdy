package banking.step3;

import banking.step3.*;

public class NormalAccount extends Account {
    private int interestRate; 

    public NormalAccount(String accountId, String customerName, int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(int money) {
        int interest = (int)(money * (this.interestRate / 100.0));
        super.deposit(money + interest);
    }

    @Override
    public void showAccountInfo() {
        System.out.println("계좌번호 : " + getAccountId());
        System.out.println("고객이름 : " + getCustomerName());
        System.out.println("잔고 : " + getBalance());
        System.out.println("기본이자 : " + interestRate + "%");
    }

    public int getInterestRate() {
        return interestRate;
    }
}
