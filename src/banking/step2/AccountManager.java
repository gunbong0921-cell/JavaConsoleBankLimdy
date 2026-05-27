package banking.step2;

import banking.step2.Account;

public class AccountManager {
    private Account[] accounts = new Account[100];
    private int numOfAccounts = 0;

    public void makeAccount(Account acc) {
        if (numOfAccounts >= accounts.length) {
            System.out.println("더 이상 계좌를 개설할 수 없습니다.");
            return;
        }
        accounts[numOfAccounts++] = acc;
        System.out.println("계좌 개설이 완료되었습니다.");
    }

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