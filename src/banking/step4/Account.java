package banking.step4;

import java.util.Objects;

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
        System.out.printf
        ("계좌번호: %s, 고객이름: %s, 잔고: %d\n", accountId, 
        		customerName, balance);
    }
    
    @Override
    /*
    hashCode()
    : 객체를 빠르고 효율적으로 찾기 위해 객체를 식별하는 하나의 정수값(해시 알고리즘 결과)을
    	반환하는 hashcode() 메서드 사용
     */
    /*
    hashCode() 핵심규칙
    1.일관성 : equals() 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션
    	실행 중에 언제 호출해도 항상 같은 값을 반환해야 한다.
    2.동등성 : 두 객체가 equals() 메서드로 인해 같다고 판단되면, 두 객체의 
    	hashCode() 반환값은 같아야 한다.
    3.독립성 : 두 객체가 equals()로 다르다고 판단되더라도, 두 객체의 hashCode()
    	값이 서로 다를 필요는 없다. 하지만 성능 향상을 위해 다른 값을 반환하는 것이
    	좋다. 
     */
    public int hashCode() { 
    	return Objects.hash(accountId);
    }
    
    @Override
    /*
    equals()를 오버라이딩하여 객체의 비교값을 재정의 했으므로 반드시 hashCode()도 함께
    오버라이딩해서 두 객체가 같은 해시 코드를 반환하도록 해야함.
     */
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null || getClass() != obj.getClass()) 
    		return false;
    	Account other = (Account) obj;
    	return Objects.equals(accountId, other.accountId);
    }
}

