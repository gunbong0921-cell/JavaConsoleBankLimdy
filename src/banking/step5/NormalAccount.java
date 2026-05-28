package banking.step5;

public class NormalAccount extends Account {
    private int interestRate; 

    public NormalAccount(String accountId, String customerName, 
    		int balance, int interestRate) {
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
        super.showAccountInfo(); 
        System.out.printf("기본이자: %d%%\n", interestRate); 
    }

    public int getInterestRate() {
        return interestRate;
    }
}

