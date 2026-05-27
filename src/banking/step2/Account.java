package banking.step2;

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

    protected void addPureMoney(int money) {
        this.balance += money;
    }

    public void withdraw(int money) {
        if (this.balance >= money) {
            this.balance -= money;
        } else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    public String getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public int getBalance() { return balance; }

    public void showAccountInfo() {
        System.out.println("계좌번호>" + accountId);
        System.out.println("고객이름>" + customerName);
        System.out.println("잔고>" + balance);
    }
}