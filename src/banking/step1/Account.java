package banking.step1;

public class Account {
    private String accountId;   
    private String customerName; 
    private int balance;        

    public Account(String accountId, String customerName, int balance) {
        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = balance;
    }

    public void deposit(int money) {
        this.balance += money;
    }

    public void withdraw(int money) {
        if (this.balance >= money) {
            this.balance -= money;
        } 
        else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    public String getAccountId() { return accountId; }
    public int getBalance() { return balance; }

    public void showAccountInfo() {
        System.out.printf("계좌번호: %s | 고객이름: %s | 잔고: %,d원\n", accountId, customerName, balance);
    }
}