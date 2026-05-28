package banking.step4;

import banking.step4.*;

public class NormalAccount extends Account {
    private int interestRate; 

    public NormalAccount(String accountId, String customerName, 
    		int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(int money) {
        int interest = (int)(money * (this.interestRate / 100.0));
        super.deposit(money + interest);
    }

    // 보통계좌 출력: 첫 줄(부모 객체) 실행 후, 둘째 줄에 기본이자를 출력하고 재실행
    @Override
    public void showAccountInfo() {
    	super.showAccountInfo(); // 첫 줄 출력 (계좌번호, 고객이름, 잔고)
        System.out.printf("기본이자: %d%%\n", interestRate); 
    }

    public int getInterestRate() {
        return interestRate;
    }
}

