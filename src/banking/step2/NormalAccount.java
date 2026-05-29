package banking.step2;

import banking.step2.*;
/*
상속선언과 멤버변수
-extends Account
 : 부모클래스인 Account로부터 계좌번호(accountId), 고객이름(customerName),
 잔액(balance)속성과 입출금 기능을 상속받음
-interestRate
 : 보통계좌의 고유한 특징인 기본이자율을 저장하기 위해 추가된 일반 멤버 변수
 */
public class NormalAccount extends Account {
    private int interestRate; 
    /*
    생성자
    -자식 객체가 생성될때 부모의 데이터가 먼저 초기화되어야 하므로, super()를
    통해 부모클래스(Account)의 생성자를 호출하여 계좌번호, 이름, 잔액을 구성
    -자기 자신의 데이터인 기본이자율(interestRate)초기화
     */
    public NormalAccount(String accountId, String customerName, int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }
    /*
    입금메서드 오버라이딩(@Override deposit)
    1.이자계산로직
    interestRate/100.0을 통해 정수형 이자율을 실수(double)로 변환하여 정확
    한 퍼센트 계산을 수행. 계산된 이자금액을 원금(money)에 더한 후 int로 형변환
    2.super.deposit(totalDeposit)
    부모가 물려준 deposit메서드는 단순히 잔액변수에 값을 더하는 기능을 함. 계산된
    원금+이자의 최종금액을 부모의 메서드로 넘겨주어 안전하게 잔액 갱신
     */
    @Override
    public void deposit(int money) {
        int totalDeposit = money + (int)(money * (interestRate / 100.0));
        super.deposit(totalDeposit);
    }
    /*
    정보출력 및 Getter메서드
    1.showAccountInfo()
    부모의 출력메서드를 super.showAccountInfo()로 먼저 실행하여 기본 정보를
    출력한 뒤, 그 아래에 "기본이자>X%"라는 문구를 추가로 출력하도록 재정의
    2.getInterrestRate()
    private으로 보호되어 있는 이자율 데이터를 외부 클래스에서 읽을 수 있도록 제공
    하는 Getter메서드
     */
    @Override
    public void showAccountInfo() {
        super.showAccountInfo();
        System.out.println("기본이자>" + interestRate + "%");
    }
    public int getInterestRate() {
        return interestRate;
    }
}
