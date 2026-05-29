package banking.step3;

import banking.step3.Account;
import java.util.Scanner;

public class AccountManager {
	
    private Account[] accounts = new Account[100];
    private int numOfAccounts = 0;
    /*
    멤버변수 및 계좌개설
    -2단계와 동일하게 최대 100갸의 계좌를 관리하는 배열과 카운트변수를 가짐
    -Account클래스가 abstract(추상클래스)가 되었기 때문에 매개변수 Account acc
    에는 무조건 실체가 있는 자식 객체(NormalAccount등)만 들어오게 됨
     */
    public void makeAccount(Account acc) {
        if (numOfAccounts >= accounts.length) {
            System.out.println("더 이상 계좌를 생성할 수 없습니다.");
            return;
        }
        accounts[numOfAccounts++] = acc;
        System.out.println("신규계좌개설이 완료되었습니다.");
    }

    /*
    예외 검증이 추가된 입금로직
    1.음수제한 : 마이너스금액을 입금을 제한하기 위한 예외처리
    2.단위제한 : money % 500 != 0 조건을 통해 500원 동전 단위로 나누어 떨어지지
    			않는 금액의 입금을 차단
    3.예외전가(throws Exception)
     : 위 규칙을 위반하면 대화상자를 띄우는 대신 Exception 객체를 생성하여 이 메서드를
     호출한 main클래스로 처리를 넘겨버림			
     */
    public void depositMoney(String id, int money) throws Exception {
        // 규칙 1: 음수 입금 제한 예외
        if (money < 0) {
            throw new Exception("음수는 입금불가");
        }
        // 규칙 2: 500원 단위 입금 제한 예외
        if (money % 500 != 0) {
            throw new Exception("500원 단위로만 입금가능함");
        }

        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountId().equals(id)) {
                accounts[i].deposit(money);
                System.out.println("입금이 완료되었습니다.");
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }

    /*
    잔액부족구제흐름이 반영된 출금로직
    1.예외가로채기(try-catch)
     : account[i].withdraw(money)를 실행했을 때 잔액이 부족하면 Account 객체는 예외를
    던짐. 매니저는 프로그램이 멈추지 않도록 이를 catch문으로 붙잡음
    2.사용자 의사 재확인
     : 예외 메세지가 출력될 경우 출금을 취소하는 대신 전액출금 의사 확인(y or n)
    3.전액출금연계
     : 사용자가 y또는 Y(equalsIgnoreCase 적용)을 입력하면 Account에 새로 추가된
     withdrawAll()메서드를 작동시켜 잔액을 0으로 만들고 전액인출해줌 
     */
    public void withdrawMoney(String id, int money, Scanner sc) {
    	
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountId().equals(id)) {
                try {
                    accounts[i].withdraw(money);
                    System.out.println("출금이 완료되었습니다.");
                } 
                catch (Exception e) {
                    if (e.getMessage().equals("잔액부족")) {
                        // 영상 흐름 기획: 예외 차단 후 사용자 의사 재확인
                        System.out.print("잔고부족, 금액전체를 출금할까요? (y or n) : ");
                        String ans = sc.next();
                        if (ans.equalsIgnoreCase("y")) {
                            int drawnMoney = accounts[i].withdrawAll();
                            System.out.println("전액 " + drawnMoney + "원 출금이 완료되었습니다.");
                        } 
                        else {
                            System.out.println("출금이 취소되었습니다.");
                        }
                    }
                }
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }
    /*
    전체계좌정보출력
    -accounts[I].showAccountInfo()를 호출할때 Account클래스에서 해당 메서드를 
    abstract로 강제해 두었기 때문에 어떤 자식 계좌가 들어있든 에러없이 완벽하게
    다형성 출력이 보장됨
     */
    public void showAllAccountInfo() {
    	
        if (numOfAccounts == 0) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        System.out.println("***계좌정보출력***");
        
        for (int i = 0; i < numOfAccounts; i++) {
            System.out.println("-------계좌정보-------");
            accounts[i].showAccountInfo();
            System.out.println("--------------------");
        }
        System.out.println("전체계좌정보가 출력되었습니다.");
        System.out.println("***************************");
    }
}