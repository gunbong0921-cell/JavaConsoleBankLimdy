package banking.step5;

import java.util.Scanner;

public class BankingSystemMain {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        Scanner sc = new Scanner(System.in);
        
        // [중요] 프로그램 시작 시 기존 데이터 파일 복원 시도
        manager.loadInfo();

        while (true) {
            System.out.println("====================Menu====================");
            System.out.println("1.계좌개설, 2.입금, 3.출금, 4.계좌정보출력");
            System.out.println("5.계좌정보삭제, 6.종료");
            System.out.println("============================================");
            System.out.print("선택: ");
            
            int menu = sc.nextInt();

            if (menu == 6) {
                // [중요] 종료 선택 시 데이터를 파일에 백업 저장
                manager.saveInfo();
                System.out.println("***프로그램종료***");
                break;
            }

            switch (menu) {
                case 1:
                    System.out.println("***계좌개설***");
                    System.out.println("---계좌선택---");
                    System.out.println("1.보통계좌");
                    System.out.println("2.신용신뢰계좌");
                    System.out.print("선택: ");
                    int type = sc.nextInt();
                    
                    System.out.print("계좌번호: ");
                    String id = sc.next();
                    System.out.print("고객이름: ");
                    String name = sc.next();
                    System.out.print("잔고: ");
                    int balance = sc.nextInt();
                    System.out.print("기본이자%(정수형태): ");
                    int rate = sc.nextInt();

                    if (type == 1) {
                        manager.makeAccount(new NormalAccount
                        		(id, name, balance, rate), sc);
                    } else if (type == 2) {
                        System.out.print("신용등급(A,B,C): ");
                        String grade = sc.next();
                        int creditRate = 0;
                        if (grade.equalsIgnoreCase("A")) creditRate = 7;
                        else if (grade.equalsIgnoreCase("B")) creditRate = 4;
                        else if (grade.equalsIgnoreCase("C")) creditRate = 2;

                        manager.makeAccount(new HighCreditAccount
                        		(id, name, balance, rate, creditRate, grade.toUpperCase()), sc);
                    }
                    break;

                case 2:
                    System.out.print("계좌번호: ");
                    id = sc.next();
                    System.out.print("입금액: ");
                    int deposit = sc.nextInt();
                    try {
                        manager.depositMoney(id, deposit);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("계좌번호: ");
                    id = sc.next();
                    System.out.print("출금액: ");
                    int withdraw = sc.nextInt();
                    manager.withdrawMoney(id, withdraw, sc);
                    break;

                case 4:
                    manager.showAllAccountInfo();
                    break;

                case 5:
                    System.out.println("***계좌정보삭제***");
                    System.out.print("삭제할 계좌번호를 입력하세요\n계좌번호: ");
                    id = sc.next();
                    manager.deleteAccount(id);
                    break;

                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
            }
        }
        sc.close();
    }
}