package banking.step1;

public class AccountManager {
    private Account[] accounts = new Account[100];
    private int numOfAccounts = 0;
    
    /*
    계좌개설(makeAccount) : 새 계좌 생성
    - 'numOfAccounts++'(후위 연산자)를 사용하여 현재 개수 위치에 새 Account 객체를 넣은 후, 
    	계좌 개수를 1 증가시킴 (예: 처음엔 accounts[0]에 넣고 개수는 1이 됨)
     */
    public void makeAccount(String id, String name, int balance) {
        accounts[numOfAccounts++] = new Account(id, name, balance);
        System.out.println("계좌 개설이 완료되었습니다.");
    }
    //입금처리(depositMoney) 
    public void depositMoney(String id, int money) {
    	/*
    	1. for문을 돌며 현재 저장된 계좌 갯수만큼 순회하며 검색한다
    	2. 문자열 비교 메서드.equals()메서드를 호출해 입금한 후 return으로 함수 종료
    	3. 일치하는 계좌를 찾은 후 해당 계좌의 deposit()메서드를 호출해 입금처리하고
    		함수를 종료함
    	4. 루프를 전부 완료하고도 찾지 못하면 해당 메시지 출력	
    	 */
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountId().equals(id)) {
                accounts[i].deposit(money);
                System.out.println("입금이 완료되었습니다.");
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }
    //출금처리(withdrawMoney)
    public void withdrawMoney(String id, int money) {
        for (int i = 0; i < numOfAccounts; i++) {
            if (accounts[i].getAccountId().equals(id)) {
            	/*
            	입금기능과 동일한 방식으로 배열을 검색. 일치하는 계좌를 찾으면 해당 계좌의
            	with()메서드를 호출. 잔액 부족 시 Account클래스의 withdraw내에서 처리
            	 */
                accounts[i].withdraw(money);
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }
    //전체계좌정보출력(showAllAccountInfo) : 개설된 모든 계좌 정보 출력
    public void showAllAccountInfo() {
        if (numOfAccounts == 0) System.out.println("등록된 계좌가 없습니다.");
        for (int i = 0; i < numOfAccounts; i++) {
            accounts[i].showAccountInfo();
        }
    }
}