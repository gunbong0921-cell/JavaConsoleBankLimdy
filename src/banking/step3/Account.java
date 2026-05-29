package banking.step3;
/*
추상클래스선언(abstract class)
: 은행 시스템에 개념상 존재하는 기본계좌가 직접 개설되는 것을 막고, 구체적인 
자식계좌(NormalAccount, HighCreditAccount등)를 통해서만 객체가 생성
되도록 강제하는 안전장치
 */
//멤버변수생성자
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
    /*
    출금기능(예외처리도입(withdraw))
    -기존에 잔액이 부족하면 단순히 콘솔에 경고창을 띄우는 것으로 그쳤다면 3단계에서는 
    자바의 예외처리매커니즘(throw, throws)을 사용
    -잔액이 부족할 경우 throw new Exception("잔액부족")을 통해 고의로 예외를
    발생시키고 메서드를 강제종료. 호출된 외부클래스(AccountManager등)에서 이 예외를
    받아 처리하도록 throws Exception을 메서드 선언부에 명시
     */
    public void withdraw(int money) throws Exception {
        if (this.balance < money) {
            throw new Exception("잔액부족");
        }
        this.balance -= money;
    }
    /*
    전액출금(withdrawAll)
    :현재 잔액을 임시변수 allMoney에 보관한 뒤 계좌 잔액을 0원으로 비우고 인출한
    잔액을 호출한 곳으로 반환(return)
     */
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
    /*
    추상메서드화
    해당 부모를 상속받는 모든 자식클래스들은 반드시 자신만의 스타일로 showAccountInfo()
    를 오버라이딩(재정의)하여 구현해야만하며, 만약 재정의하지 않으면 컴파일 에러가 발생하므로
    자식 클래스들의 출력 기능 구현을 100% 보장하는 강력한 규칙임
     */
    public abstract void showAccountInfo();
}