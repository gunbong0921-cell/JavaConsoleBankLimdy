package banking.step3;

import banking.step3.*;
//상속관계와 추상메서드의 구현
public class NormalAccount extends Account {
    private int interestRate; 
    //생성자구현(super)
    public NormalAccount(String accountId, String customerName, int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }
    /*
    입금메서드 오버라이딩(@Override deposit)
    -2단계에서 totalDeposit변수를 만들어 한번에 넘겼다면 3단계에서는 이자(interest)
    만 명확하게 한줄로 계산한뒤 부모에게 합산금액을 던져주는 방식으로 가독성 있게 코드를
    리팩토링
     */
    @Override
    public void deposit(int money) {
        int interest = (int)(money * (this.interestRate / 100.0));
        super.deposit(money + interest);
    }
    /*
    정보출력메서드의 독립적 구현(showAccountInfo)
    -2단계에서는 super.showAccountInfo():를 호출하여 부모의 출력 기능을 빌려 사용했지만
    3단계에서는 부모의 메서드가 추상메서드(abstract)로 바뀌어 메인이 사라졌기 때문에 더이상
    super.showAccountInfo()를 호출할 수 없음
    -부모의 Getter매서드(getAccountId(), getCustomerName(), getBalance()를 활용
    하여 계좌의 기본정보로부터 이자율 출력까지 모든 항목을 처음부터 끝까지 직접 출력하도록
    재정의됨
     */
    @Override
    public void showAccountInfo() {
        System.out.println("계좌번호 : " + getAccountId());
        System.out.println("고객이름 : " + getCustomerName());
        System.out.println("잔고 : " + getBalance());
        System.out.println("기본이자 : " + interestRate + "%");
    }

    public int getInterestRate() {
        return interestRate;
    }
}
