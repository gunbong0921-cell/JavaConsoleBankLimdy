package banking.step4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {
    // 배열 대신 HashSet 컬렉션 사용
    private HashSet<Account> accounts = new HashSet<>();

    // 특정 계좌번호로 계좌 객체를 찾아주는 보조 메서드
    public Account findAccountById(String id) {
        for (Account acc : accounts) {
            if (acc.getAccountId().equals(id)) {
                return acc;
            }
        }
        return null;
    }

    // 계좌 개설 및 중복 체크 로직
    public void makeAccount(Account acc, Scanner sc) {
        Account existAcc = findAccountById(acc.getAccountId());
        
        // 같은 계좌번호가 존재하는 경우
        if (existAcc != null) {
            System.out.println("중복계좌발견됨.");
            System.out.print("덮어쓸까요? (y or n) : ");
            String ans = sc.next();
            if (ans.equalsIgnoreCase("y")) {
                // 기존 객체를 삭제하고 새 객체를 셋에 추가하여 갱신 처리
                accounts.remove(existAcc);
                accounts.add(acc);
                System.out.println("새로운 정보로 갱신되었습니다.");
            } 
            else {
                System.out.println("계좌 개설이 취소되었습니다.");
            }
            return;
        }

        // 계좌 중복이 없다면 계좌개설 정상 추가
        accounts.add(acc);
        System.out.println("신규계좌개설이 완료되었습니다.");
    }

    public void depositMoney(String id, int money) throws Exception {
        if (money < 0) throw new Exception("음수는 입금불가");
        if (money % 500 != 0) throw new Exception("500원 단위로만 입금가능");
        Account acc = findAccountById(id);
        if (acc != null) {
            acc.deposit(money);
            System.out.println("입금이 완료되었습니다.");
        } 
        else {
            System.out.println("해당 계좌를 찾을 수 없습니다.");
        }
    }

    public void withdrawMoney(String id, int money, Scanner sc) {
        Account acc = findAccountById(id);
        if (acc != null) {
            try {
                acc.withdraw(money);
                System.out.println("출금이 완료되었습니다.");
            } 
            catch (Exception e) {
                if (e.getMessage().equals("잔액부족")) {
                    System.out.print("잔고부족, 금액전체를 출금할까요? (y or n) : ");
                    String ans = sc.next();
                    if (ans.equalsIgnoreCase("y")) {
                        int drawnMoney = acc.withdrawAll();
                        System.out.println("금액전체 " + drawnMoney 
                        		+ "원 출금이 완료되었습니다.");
                    } 
                    else {
                        System.out.println("출금이 취소되었습니다.");
                    }
                }
            }
        } 
        else {
            System.out.println("해당 계좌를 찾을 수 없습니다.");
        }
    }

    // 4단계 새 기능 추가(계좌 정보 삭제 로직)
    public void deleteAccount(String id) {
        Account acc = findAccountById(id);
        if (acc != null) {
            accounts.remove(acc); //요소 삭제
            System.out.println("계좌를 삭제하였습니다.");
            System.out.println("***************************");
        } 
        else {
            System.out.println("일치하는 계좌가 없습니다.");
            System.out.println("***************************");
        }
    }

    public void showAllAccountInfo() {
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        System.out.println("***계좌정보출력***");
        for (Account acc : accounts) {
            System.out.println("-------계좌정보-------");
            acc.showAccountInfo();
            System.out.println("--------------------");
        }
        System.out.println("전체계좌정보가 출력되었습니다.");
        System.out.println("***************************");
    }
}

