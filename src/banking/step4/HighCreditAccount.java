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
    
    /*
    super.super우회 및 print포맷팅(showAccountInfo)
    -자바에서 문법적으로 super.super.showAccountInfo()와 같이 상위 클래스의 메서드를 직접 호출하는
    것을 절대 금지하고 있음.
    -부모클래스인 NormalAccount의 showAccountInfo()를 호출하면 그 안에서 기본이자가 먼저 한줄로
    되어버려, 원하는 결과를 출력할 수 없음
    -따라서 부모들의 메서드를 호출하는 대신 최상위 부모가 물려준 Getter메서드 (getAccountId(), 
    getCustomerName(), getBalance())를 호출하여 첫줄에 계좌기본정보를 직접 출력하돌고 바꿈
    -둘째줄에 아버지가 물려준 getInterestRate()와 본인의 creditLevel을 엮어 한눈에 들어오는 가로 배치
    형태(기본이자: X%, 신용등급: Y)로 정돈
    -printf안에서 퍼센트 기호(%)를 문자로 출력하기 위해 이스케이프 규칙인 %%를 사용
     */
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


