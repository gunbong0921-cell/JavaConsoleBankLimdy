package banking.step3;

public abstract class Account {
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

    public void withdraw(int money) throws Exception {
        if (this.balance < money) {
            throw new Exception("잔액부족");
        }
        this.balance -= money;
    }

    public int withdrawAll() {
        int allMoney = this.balance;
        this.balance = 0;
        return allMoney;
    }

    public String getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public int getBalance() { return balance; }

    public abstract void showAccountInfo();
}