package banking.step5;
/*
상속과 직렬화자격 계승
-NormalAccount를 상속받음. 5단계에서 최상위 부모인 Account가 implements Serializable
	을 선언했기 때문에 자식인 NormalAccount와 최하위 자식인 highCreditAccount역시 자동으로
	직렬화자격(Serializable)을 부여 받음
-현 객체도 매니저 클래스에 의해 디스크파일(Accountinfo.obj)에 흔적없이 저장되고 완벽하게 복원
	될 수 있음
 */
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
    출력문구의 직관성개선(showAccountinfo)
    -super.super 접근이 안되는 제약을 극복하기 위해 상속받은 Getter메서드들을 활용하여 
    	첫 줄에 기본 정보를 직접 수동 출력하는 구조를 유지
    -기존 4단계에서 '기본이자: X%라고 출력했으나 동영상예제에 이자율: X%로 되어 있어 수정함
     */
    @Override
    public void showAccountInfo() {
        System.out.printf("계좌번호: %s, 고객이름: %s, 잔고: %d\n", getAccountId(), 
        		getCustomerName(), getBalance());
        //5단계 : '기본이자' 대신 '이자율'로 출력(동영상 파일 참조)
        System.out.printf("이자율: %d%%, 신용등급: %s\n", getInterestRate(), creditLevel);
    }
}

