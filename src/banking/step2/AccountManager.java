package banking.step2;

public class AccountManager {
    private Account[] accounts = new Account[100];
    private int numOfAccounts = 0;

    public void makeAccount(String id, String name, int balance) {
        accounts[numOfAccounts++] = new Account(id, name, balance);
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
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }

    public void showAllAccountInfo() {
        if (numOfAccounts == 0) System.out.println("등록된 계좌가 없습니다.");
        for (int i = 0; i < numOfAccounts; i++) {
            accounts[i].showAccountInfo();
        }
    }
}