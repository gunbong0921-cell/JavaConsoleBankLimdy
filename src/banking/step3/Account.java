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
    
    /*
    { return  ; }
    : 해당 메서드가 종료되면서 accountId 변수값을 호출한 곳으로
    반환하는 구문. 주로 Getter 메서드나 조회 로직에서 사용
    */
    public String getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public int getBalance() { return balance; }

    public abstract void showAccountInfo();
}