package banking.step2;
/*
상속선언 및 멤버변수
-extends NormalAccount
: 보통이율계좌(NormalAccount)를 상속받아 부모가 가진 계좌번호, 이름, 잔액,
기본이자율 속성 및 관련 메서드를 상속받음
-확장된변수
 : 일반 계좌에는 없는 신용등급 문자열과 등급별 추가 우대 이율을 저장할 독립적인 
 변수 추가		
 */
public class HighCreditAccount extends NormalAccount {
    private int creditLevelRate; 
    private String creditLevel;  
    /*
    자식클래스 생성자(super)
    -super()
     : 자식 객체가 생성될 때 부모의 데이터 멤버들이 먼저 메모리에 올라가야하며,
     부모클래스인 NormalAccount의 샐성자를 호출. 계좌번호, 이름, 잔액, 기본
     이자율을 먼저 초기화함
    -부모의 초기화가 끝난 후 자신만의 특수데이터 creditLevelRate, creditLevel을
    마저 초기화함
     */
    public HighCreditAccount(String accountId, String customerName, 
    		int balance, int interestRate, int creditLevelRate, String creditLevel) {
        super(accountId, customerName, balance, interestRate);
        this.creditLevelRate = creditLevelRate;
        this.creditLevel = creditLevel;
    }
    /*
    입금메서드 오버라이딩(@Override deposit)
    1.super.deposit(money)
    : 코드가 작성되지 않았지만 NormalAccount의 deposit메서드에는 원금+(원금*기본이자율)
    이 입금되는 로직이 있음. 이를 그대로 가져와 먼저 실행함
    2.우대이자계산
    : 입력받은 원금(money)에 자신의 우대이율(creditLevelRate)을 곱해 추가 이자계산.
    정수 연산 시 소수점이 잘리는 것을 막기위해 100.0으로 나누어 실수연산을 유도한 뒤
    최종적으로 int로 형변환함
    3.super.addPureMoney(creditInterest)
    : 계산된 우대 이자는 추가적인 이자 계산 없이 잔액에 순수하게 금액만 더해져야 하므로,
    Account를 클래스에서 만들어 두었던 protected메서드인 addPureMoney를 호출하여 안전하게
    잔액을 증가시킴
     */
    @Override
    public void deposit(int money) {
        super.deposit(money); 
        int creditInterest = (int)(money * (this.creditLevelRate / 100.0));
        super.addPureMoney(creditInterest);
    }
    /*
    정보출력메서드 오버라이딩(@Override showAccountInfo)
    1.부모의 showAccountInfo()를 호출하여 기본계좌를 먼저 화면에 출력한 뒤, 신용계좌만의
    특징인 신용등급을 맨 아래에 추가로 출력하게 함
    2.오버라이딩 덕분에 AccountManager에서 account[i].showAccountInfo()를 호출했을때,
    해당 계좌가 신용계좌라면 신용등급까지 깔끔하게 출력되는 다형성이 완성됨
     */
    @Override
    public void showAccountInfo() {
        super.showAccountInfo(); 
        System.out.println("신용등급>" + creditLevel); 
    }
}