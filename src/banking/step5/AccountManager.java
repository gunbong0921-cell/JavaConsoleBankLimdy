package banking.step5;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class AccountManager {
    private HashSet<Account> accounts = new HashSet<>();
    // 5단계 동영상에 명시된 파일 이름
    private final String FILE_NAME = "AccountInfo.obj";
    
    /*
    테이터파일저장 로직(saveInfo)
    -ObjectOutStream
     : 객체를 바이트 스트림으로 변환해주는 직렬화 전용 필터 스트림
    -동작
     : accounts라는 HashSet 객체자체를 writObject()메서드 한줄로 AccountInfo.obj파일에
     	저장됨. 셋(Set)안에 들어있던 수많은 계좌(자식계좌포함)들이 고스란히 파일로 변환되어 기록됨
    -try-with-resources
     : try(...)괄호안에 스트림을 생성하여 작업이 끝난 후 에러 발생여부와 상관없이 자원(close())
       	이 자동으로 반환되도록 절계
     */
    // 파일 저장 (직렬화)
    public void saveInfo() {
        try (ObjectOutputStream oos = new ObjectOutputStream
        		(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
            System.out.println(FILE_NAME + " 파일로 저장되었습니다.");
        } 
        catch (IOException e) {
            System.out.println("데이터 저장 오류: " + e.getMessage());
        }
    }
    
    /*
    데이터파일 복원 로직(loadInfo)
    1.역질렬화(readObject())
    디스크에 바이트 형태로 굳어있는 데이터를 다시 자바 메모리 상의 객체(HashSet)로 엮어내는
    (역질렬화)작업
    2.@SuppressWarnings("unchecked")
    파일애서 읽어온 객체가 진짜 HashSet<Account>타입이 맞는지 컴파일러가 완전히 확신할 수 
    없기 때문에 경고(Warning)를 띄움. 개발자가 안전함을 담보하니 경고를 무시하라고 지시하는
    어노테이션
    3.멀티캐치( | )
    파일 입출력오류(IOException)와 더불어 저장할 당시의 클래스 설계도를 찾을 수 없는 오류
    (ClassNotFoundException)를 한곳에서 묶어 처리
     */
    // 파일 로드 (역직렬화)
    @SuppressWarnings("unchecked")
    public void loadInfo() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println(FILE_NAME + " 파일없음");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream
        		(new FileInputStream(file))) {
            accounts = (HashSet<Account>) ois.readObject();
            System.out.println(FILE_NAME + " 복원완료");
        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("데이터 복원 오류: " + e.getMessage());
        }
    }

    public Account findAccountById(String id) {
        for (Account acc : accounts) {
            if (acc.getAccountId().equals(id)) return acc;
        }
        return null;
    }

    public void makeAccount(Account acc, Scanner sc) {
        Account existAcc = findAccountById(acc.getAccountId());
        if (existAcc != null) {
            System.out.println("중복계좌발견됨.");
            System.out.print("덮어쓸까요? (y or n) : ");
            String ans = sc.next();
            if (ans.equalsIgnoreCase("y")) {
                accounts.remove(existAcc);
                accounts.add(acc);
                System.out.println("새로운 정보로 갱신되었습니다.");
            } 
            else {
                System.out.println("계좌 개설이 취소되었습니다.");
            }
            return;
        }
        accounts.add(acc);
        System.out.println("계좌개설이 완료되었습니다.");
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
                        System.out.println("금액전체 " + drawnMoney + "원 출금이 완료되었습니다.");
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

    public void deleteAccount(String id) {
        Account acc = findAccountById(id);
        if (acc != null) {
            accounts.remove(acc);
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
