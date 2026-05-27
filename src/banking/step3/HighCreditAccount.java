package banking.step3;

public class HighCreditAccount extends NormalAccount {
    private int creditLevelRate; 
    private String creditLevel;  
 
    public HighCreditAccount(String accountId, String customerName, int balance, int interestRate, int creditLevelRate, String creditLevel) {
        super(accountId, customerName, balance, interestRate);
        this.creditLevelRate = creditLevelRate;
        this.creditLevel = creditLevel;
    }

    @Override
    public void deposit(int money) {
        super.deposit(money); 
        int creditInterest = (int)(money * (this.creditLevelRate / 100.0));
        super.addPureMoney(creditInterest);
    }

    @Override
    public void showAccountInfo() {
        super.showAccountInfo(); 
        System.out.println("신용등급>" + creditLevel); 
    }
}