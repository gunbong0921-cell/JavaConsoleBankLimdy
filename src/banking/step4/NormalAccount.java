package banking.step4;

import banking.step4.*;
//상속관계와 필드구성
public class NormalAccount extends Account {
    private int interestRate; //기본이자율(정수형태)
    //생성자와 데이터 초기화(super)
    public NormalAccount(String accountId, String customerName, 
    		int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }
    //입금메서드 오버라이딩각 클래스 생성 및 설명 작성
    @Override
    public void deposit(int money) {
        int interest = (int)(money * (this.interestRate / 100.0));
        super.deposit(money + interest);
    }
    
    /*
    super를 활용한 출력양식의 규격화(showAccountInfo)
    -4단계 Account클래스의 showAccountInfi()는 계좌번호: %s, super.showAccountInfo()
    로 호출하여 첫번째 줄에 완벽하게 출력됨
    -직후 두번째 줄에 본인의 특화 정보인 "기본이자"를 printf와 이스케이프문자인 %%를 사용해
    가로로 매칭하여 마무리 지음
     */
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

