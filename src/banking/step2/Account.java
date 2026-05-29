package banking.step2;
/*
멤버변수(속성/필드)
-계좌 고유의 데이터를 저장
-private으로 지정되어 있어 클래스 외부나 상속받은 자식 클래스에서도 변수에 직접접근
 (수정/읽기)할 수 없음. 그러므로 아래 정의된 메서드를 통해서만 안전하게 접근허용됨
 */
public class Account {
    private String accountId;   
    private String customerName; 
    private int balance;        
    //생성자
    public Account(String accountId, String customerName, int balance) {
        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = balance;
    }
    //입금처리
    public void deposit(int money) {
        this.balance += money;
    }
    /*
    순수금액입금처리
    -2단계에서 추가된 핵심기능. 일반적인 deposit 메서드와 기능은 같으나, 접근 제어자가 
    	protected로 설정되어 있음
    -protected는 동일한 패키지 내에 있거나, 해당 클래스를 상속받은 자식 클래스에서만 호출
    	할 수 있다. 상속 구조에서 자식클래스(이율이 붙는 계좌)가 기본 잔액을 직접 조작해야
    	할때 안전하게 사용하기 위해 사용한 메서드
     */
    protected void addPureMoney(int money) {
        this.balance += money;
    }
    //출금처리
    public void withdraw(int money) {
        if (this.balance >= money) {
            this.balance -= money;
        } else {
            System.out.println("잔액이 부족합니다.");
        }
    }
    /*
    Getter메서드(getAccountId, getCustomerName, getBalance)
    -멤버변수가 private이므로 외부클래스(AccountManager등)에서 계좌번호나 이름을
    	읽어들일 수 있도록 열어둔 우회통로(Getter)
    -값을 수정하는 Setter는 제공하지 않아 무결성(안정성)유지	
     */
    public String getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public int getBalance() { return balance; }
    
    public void showAccountInfo() {
        System.out.println("계좌번호>" + accountId);
        System.out.println("고객이름>" + customerName);
        System.out.println("잔고>" + balance);
    }
}