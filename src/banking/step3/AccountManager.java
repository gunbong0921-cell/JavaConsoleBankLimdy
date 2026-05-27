package banking.step3;

import banking.step3.Account;
import java.util.Scanner;

public class AccountManager {
	
    private Account[] accounts = new Account[100];
    private int numOfAccounts = 0;

    public void makeAccount(Account acc) {
        if (numOfAccounts >= accounts.length) {
            System.out.println("더 이상 계좌를 생성할 수 없습니다.");
            return;
        }
        accounts[numOfAccounts++] = acc;
        System.out.println("신규계좌개설이 완료되었습니다.");
    }

    // 영상 속 복합 조건 예외처리가 반영된 입금 비즈니스 로직
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

    // 영상 속 잔액 부족 시 전액 출금 선택 흐름이 반영된 출금 비즈니스 로직
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