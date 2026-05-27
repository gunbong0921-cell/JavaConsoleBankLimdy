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
    	System.out.println("***계좌정보출력***");
        System.out.println("-------------");
        System.out.println("계좌번호 : " + accountId);
        System.out.println("고객이름 : " + customerName);
        System.out.println("잔고 : " + balance);
        System.out.println("-------------");
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }
}