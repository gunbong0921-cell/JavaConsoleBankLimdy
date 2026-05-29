package banking.step5;
//클래스상속과 직렬화 자격승계
public class NormalAccount extends Account {
    private int interestRate; 
    //생성자 구현을 통한 인스턴스 생성(super)
    public NormalAccount(String accountId, String customerName, 
    		int balance, int interestRate) {
        super(accountId, customerName, balance);
        this.interestRate = interestRate;
    }
    //입금메서드 오버라이딩(@Override deposit)
    @Override
    public void deposit(int money) {
    	//원금에 이자율을 곱해 이번 입금으로 발생한 이자 계산
        int interest = (int)(money * (this.interestRate / 100.0));
        //(원금+이자)를 부모의 deposit으로 보내 최종잔액증가
        super.deposit(money + interest);
    }
    /*
    super를 다시 활용한 출력 리팩토링(showAccountInfo)
    -3단계에서 부모 메서드가 완전히 추상메서도여서 super.showAccountInfo()를 부를 수
    	없었지만, 4~5단계에 이르러 부모클래스인 Account가 printf형식의 한줄 출력을
    	직접 제공하게 됨
    -따라서 super.showAccountInfo()를 적극적으로 호출하여 첫번째줄에 계좌기본정보를
    	 출력한 뒤, 두번째 줄에 본인의  이자율 정보를 가로 배치 양식으로 이어서 출력되
    	 도록 정돈함
     */
    @Override
    public void showAccountInfo() {
        super.showAccountInfo(); 
        System.out.printf("기본이자: %d%%\n", interestRate); 
    }

    public int getInterestRate() {
        return interestRate;
    }
}

