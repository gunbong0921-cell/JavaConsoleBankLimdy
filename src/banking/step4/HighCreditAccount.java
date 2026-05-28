package banking.step4;

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

    // 신용신뢰계좌 출력: 첫 줄(부모 객체 정보) 실행 후, 둘째 줄에 기본이자와 신용등급을 가로로 이어서 출력
    @Override
    public void showAccountInfo() {
        // Account 클래스의 정보(첫 줄)를 실행 처리까지 완벽히 수행하기 위해 
        // super.super 구조를 우회하여 계좌 기본 데이터 수동 매칭 출력
    	 System.out.printf("계좌번호: %s, 고객이름: %s, 잔고: %d\n", getAccountId(),
                 getCustomerName(), getBalance());
        
        // 둘째 줄: 기본이자와 신용등급 정보를 한 라인에 가로로 이어서 배치한 후 실행
        System.out.printf("기본이자: %d%%, 신용등급: %s\n", getInterestRate(), creditLevel);  
    }
}


