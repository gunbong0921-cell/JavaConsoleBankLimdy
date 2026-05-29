package banking.step2;

import banking.step2.Account;
/*
멤버변수 : 자바의 상속 특성으로 Account 외에 상속받은 자식클래스
	(HighCreditAccount, SavingAccount 등)의 객체도 함께
	저장할 수 있음(다형성)
 */
public class AccountManager {
    private Account[] accounts = new Account[100];
    private int numOfAccounts = 0;
    /*
    객체를 직접 받는 계좌 개설(makeAccount)
    1.다형성매개변수
    : 1단계에서는 각각 객체를 생성했지만 2단계에서는 외부(main등)에서 이미
    생성된 Account acc객체의 정보를 전부 넘겨받음. 보통계좌와 자식클래스에
    속하는 신용신뢰계좌 전부 해당 메서드 하나로 모두 등록할 수 있게 됨
    2.예외처리
    : 배열의 크기(100개)를 초과하여 데이터를 입력하려 할 때 프로그램의 오류
    (ArrayIndexOutOfBoundsException)를 방지하기 위해 배열이 가득 찼는지
    검사하는 안전장치(if)가 추가됨 
     */
    public void makeAccount(Account acc) {
        if (numOfAccounts >= accounts.length) {
            System.out.println("더 이상 계좌를 개설할 수 없습니다.");
            return;
        }
        accounts[numOfAccounts++] = acc;
        System.out.println("계좌 개설이 완료되었습니다.");
    }
    /*
    입금처리
    Getter 메서드(getAccountId())가 추가되면서, accounts[i].getAccountId().equals(id) 
    형태로 정상적이고 안전하게 계좌번호를 비교할 수 있게 됨.
     */
    public void depositMoney(String id, int money) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountId().equals(id)) {
                accounts[i].deposit(money);
                System.out.println("입금이 완료되었습니다.");
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }
    //출금처리
    public void withdrawMoney(String id, int money) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountId().equals(id)) {
                accounts[i].withdraw(money);
                System.out.println("출금이 완료되었습니다.");
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }
    //전체계좌정보출력: 출력의 시작과 끝을 알리는 안내 문구들이 추가되어 사용자 편의성이 좋아짐
    public void showAllAccountInfo() {
        if (numOfAccounts == 0) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        
        System.out.println("***계좌정보출력***");
        for (int i = 0; i < numOfAccounts; i++) {
            System.out.println("-------------");
            accounts[i].showAccountInfo();
            System.out.println("-------------");
        }
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }
}