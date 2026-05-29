package banking.step3;
/*
클래스상속관계구조(extends)
1.상속구조
 : 보통이율계좌인 NormalAccount를 상속받고 다시 추상 클래스인 Account를 상속받는 3대 
 계층구조(Multi-level inheritance)의 최하위 자식클래스임
2.의무사항
 : 최상위 부모인 Account 클래스에서 showAccountInfo()를 추상메서드로 선언했기 때문에
 자식인 NormalAccount나 최하위 자식인 현 클래스 중 한 곳에서 반드시 이를 구체적으로 구현
 (오버라이딩)해야 프로그램이 성립된  
 */
public class HighCreditAccount extends NormalAccount {
	//멤버변수와 생성자
    private int creditLevelRate; //신용등급별 우대 이율 (7, 4, 2)
    private String creditLevel;  //신용등급 등급명 (A, B, C)
 
    public HighCreditAccount(String accountId, String customerName, 
    		int balance, int interestRate, int creditLevelRate, String creditLevel) {
        super(accountId, customerName, balance, interestRate);
        this.creditLevelRate = creditLevelRate;
        this.creditLevel = creditLevel;
    }
    /*
    입금메서드 오버라이딩(@Override deposit)
    1.super.deposit(money)
    NormalAccount로 금액을 보내(원금+기본이자)가 먼저 적용되도록 만듬
    2.우대이자가산
    직후 들어온 원금에 자신의 신용우대이율(creditLevelRate)을 계산하여 추가 보너스이자를 구함
    3.super.addPureMoney(...)
    보너스 이자에는 더이상 추가 이자가 붙으면 안되므로 최상위 부모(Account)가 제공하는 안전한 
    직접 잔액 증식 메서드 addPureMoney를 사용하여 마무리
     */
    @Override
    public void deposit(int money) {
        super.deposit(money); 
        int creditInterest = (int)(money * (this.creditLevelRate / 100.0));
        super.addPureMoney(creditInterest);
    }
    /*
    정보출력메서드 오버라이딩(@Override showAccountInfo)
    -부모인 NormalAccount가 가진 출력기능(계좌기본정보+기본이자율)을 super로 먼저 실행한 후
    본인의 정체성인 신용등급을 덧붙여 출력
    -추상메서드의실현
     : 최상위 부모인 Account에서 정의했던 public abstract void showAccountInfo()에서
     다형성을 이용해 일괄출력하게 제어할 수 있음
     */
    @Override
    public void showAccountInfo() {
        super.showAccountInfo(); 
        System.out.println("신용등급>" + creditLevel); 
    }
}