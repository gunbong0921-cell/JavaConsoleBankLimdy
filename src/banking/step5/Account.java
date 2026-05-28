package banking.step5;

import java.io.Serializable;
import java.util.Objects;

// obj파일 저장을 위해 Serializable을 implements로 인터페이스 구현
public abstract class Account implements Serializable {
	/*
	버전 호환성 유지
	: 직렬화된 데이터의 버전(ID)과 클래스의 버전이 다르면 InvalidClassException
	에러 발생. serialVersionUID를 1L(직렬화 버전을 1로 고정)로 고정하면 클래스가
	수정되더라도 강제로 호환성을 유지하여 역질렬화를 성공시키기 유리함
	
	자동생성방지
	: 이를 명시하지 않으면 자바 컴파일러가 클래스의 구조(메서드, 필드 등)를 바탕으로
	해시값을 자동으로 생성함. 코드를 약간 수정해도 ID가 바뀌어 이전에 직렬화한 
	데이터를 읽지 못하는 문제가 발생할 수 있음
	*/
	/*
	※권장사항 : 실무환경에서는 버전관리가 중요하므로, 호환성을 깨뜨리는 대규모 업데이트가
		아닌 이상 일반 적으로 1L로 고정하거나 IDE(IntelliJ, Eclipse)에서 제공하는 
		자동생성 기능을 사용할 것을 권장함
	 */
    private static final long serialVersionUID = 1L;
    
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

    public void withdraw(int money) throws Exception {
        if (this.balance < money) {
            throw new Exception("잔액부족");
        }
        this.balance -= money;
    }

    public int withdrawAll() {
        int allMoney = this.balance;
        this.balance = 0;
        return allMoney;
    }

    public String getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public int getBalance() { return balance; }

    public void showAccountInfo() {
        System.out.printf("계좌번호: %s, 고객이름: %s, 잔고: %d\n", accountId, 
        		customerName, balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        /*
        타입비교조건
        1. obj == null
        : 비교하려는 대상이 null인지 확인. null이라면 더 이상 비교할
        필요가 없으므로 false를 반환
        2. getClass() != obj.getClass()
        : 현재 객체(this)와 비교대상(obj)의 클래스 타입이 정확히
        일치하는지 확인. 클래스가 다르면 false를 반환
         */
        if (obj == null || getClass() != obj.getClass()) 
        	return false;
        Account other = (Account) obj;
        return Objects.equals(accountId, other.accountId);
    }
}

