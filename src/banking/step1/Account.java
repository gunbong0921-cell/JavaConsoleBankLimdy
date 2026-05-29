package banking.step1;

public class Account {
	/*
	private 접근 제한자를 사용하여 외부에서 직접 수정할 수 없도록 보호(정보은닉)
	 */
    private String accountId; //계좌번호 
    private String customerName; //고객이름
    private int balance; //잔액       

    public Account(String accountId, String customerName, int balance) { //생성자
    	/*
    	this 키워드
    	: 매개변수의 이름과 멤버 변수의 이름이 같기 때문에, 
    	this.accountId를 통해 "이 객체의 멤버 변수"임을 명확히 구분
    	 */
        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = balance;
    }
    //임금(deposit) : 외부에서 입금할 금액(money)을 받아 현재 잔액(balance)에 더함
    public void deposit(int money) {
        this.balance += money;
    }
    //출금(withdraw) : 외부에서 출금할 금액(money)을 받아 잔액 차감
    public void withdraw(int money) {
    	/*
    	if or else 조건문
    	1. 잔액이 충분하면 차감(-=)을 진행
    	2. 잔액이 부족하면 경고메세지 출력 후 출금 취소
    	 */
        if (this.balance >= money) {
            this.balance -= money;
        } 
        else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    public String getAccountId() { return accountId; }
    public int getBalance() { return balance; }

    public void showAccountInfo() {
    	System.out.println("***계좌정보출력***");
        System.out.println("-------------");
        System.out.println("계좌번호 : " + accountId);
        System.out.println("고객이름 : " + customerName);
        System.out.println("잔고 : " + balance);
        System.out.println("-------------");
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }
}