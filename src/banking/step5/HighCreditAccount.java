package banking.step5;

public class HighCreditAccount extends NormalAccount {
    private int creditLevelRate; 
    private String creditLevel;  

    public HighCreditAccount(String accountId, String customerName, 
    		int balance, int interestRate, int creditLevelRate, String creditLevel) {
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
        System.out.printf("계좌번호: %s, 고객이름: %s, 잔고: %d\n", getAccountId(), 
        		getCustomerName(), getBalance());
        //5단계 : '기본이자' 대신 '이자율'로 출력(동영상 파일 참조)
        System.out.printf("이자율: %d%%, 신용등급: %s\n", getInterestRate(), creditLevel);
    }
}

