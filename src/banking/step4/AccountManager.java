package banking.step4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {
    /*
    컬렉션 프레임워크 도입(HashSet)
    1.기존방식의한계
    3단계까지는 100갸짜리 배열을 썼기 때문에 100개가 넘어가면 더 이상 계좌입력을 하지
    못했으므로(makeAccount내부제한) 중간에 계좌를 삭제하면 배열의 빈칸을 앞으로 당겨
    채우는 연산이 매우 복잡했음
    2.도입효과
    저장용량이 자동으로 늘어남. 또한 HashSet은 내부적으로 중복요소를 허용하지 않는 특성을 
    가짐.(중복여부를 가릴때 Account클래스에 만들어둔 hashCode()와 equals()가 사용됨)
     */
	// 배열 대신 HashSet 컬렉션 사용
    private HashSet<Account> accounts = new HashSet<>();
    
    /*
    내부검색보조메서드(findAccountById)
    -입금, 출금, 삭제, 중복체크 등 모든 업무의 선행 조건인 "특정 계좌번호를 가진 객체 찾기"
    	를 전담함
    -향상된 for문(for-each)을 사용해 HashSet전체를 순회하며 계좌번호를 대조하고 찾으면
     해당 객체를 못찾으면 null을 반환
     */
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
    	/*
    	중복체크 및 데이터 갱신로직(makeAccount)
    	1.시나리오 도입
    	무조건 새 계좌를 추가하던 이전과 달리 findAccountById로 기존에 등록된
    	계좌가 있는지 우선 검색
    	2.덮어쓰기 기능
    	이미 존재하는 계좌번호라면 사용자에게 덮어쓸 것인지 물어본 후 동의(y)시
    	accounts.remove()로 예전 데이터를 지우고 accounts.add()로 최신데이터
    	를 밀어넣는 데이터갱신(Update)로직을 안전하게 처리
    	 */
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
    /*
    계좌삭제로직(deleteAccount)
    -4단계에서 새로 추가된 계좌삭제(Delete)기능
    -순수 고정 배열과 달리 HashSet은 accounts.remove(acc)한줄이면 내부자료구조
    가 알아서 완벽하게 정리됨
     */
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

