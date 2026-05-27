package banking.step2;

import java.util.Scanner;

import banking.step2.AccountManager;

public class BankingSystemMain {
	
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        while (true) {
            System.out.println("\n-----Menu-----");
            System.out.println("1.계좌개설");
            System.out.println("2.입   금");
            System.out.println("3.출   금");
            System.out.println("4.계좌정보출력");
            System.out.println("5.프로그램종료");
            System.out.print("선택: ");
            int choice = sc.nextInt();

            if (choice == ICustomDefine.MAKE) {
            	System.out.println("***신규계좌개설***");
                System.out.println("\n-----계좌선택-----");
                System.out.println("1.보통계좌");
                System.out.println("2.신용신뢰계좌");
                System.out.print("선택: ");
                int accountType = sc.nextInt();

                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("고객이름: "); 
                String name = sc.next();
                System.out.print("잔고: "); 
                int balance = sc.nextInt();
                System.out.print("기본이자%(정수의형태로입력): "); 
                int interestRate = sc.nextInt();

                if (accountType == 1) {
                    NormalAccount normalAcc = new NormalAccount(id, name, balance, interestRate);
                    manager.makeAccount(normalAcc);
                }
                
                if (accountType == 2) {
                    System.out.print("신용등급 선택(A, B, C등급): ");
                    String creditStr = sc.next(); 
                    int creditRate = 0;

                    if (creditStr.toUpperCase().equals("A")) {
                        creditRate = ICustomDefine.LEVEL_A;
                    } else if (creditStr.toUpperCase().equals("B")) {
                        creditRate = ICustomDefine.LEVEL_B;
                    } else if (creditStr.toUpperCase().equals("C")) {
                        creditRate = ICustomDefine.LEVEL_C;
                    } else {
                        System.out.println("잘못된 등급 선택입니다. 기본 0% 우대이율이 적용됩니다.");
                    }

                    HighCreditAccount highAcc = new HighCreditAccount
                    		(id, name, balance, interestRate, creditRate, creditStr.toUpperCase());
                    manager.makeAccount(highAcc);
                } 
                else {
                    System.out.println("잘못된 계좌 종류를 선택하셨습니다.");
                }

            } 
            else if (choice == ICustomDefine.DEPOSIT) {
            	System.out.println("***입   금***");
                System.out.println("계좌번호와 입금할 금액을 입력하세요.");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("입금액: "); 
                int money = sc.nextInt();
                manager.depositMoney(id, money);
            } 
            else if (choice == ICustomDefine.WITHDRAW) {
            	System.out.println("***출   금***");
                System.out.println("계좌번호와 출금할 금액을 입력하세요.");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("출금액: "); 
                int money = sc.nextInt();
                manager.withdrawMoney(id, money);
            } 
            else if (choice == ICustomDefine.INQUIRE) {
                manager.showAllAccountInfo();
            } 
            else if (choice == ICustomDefine.EXIT) {
                System.out.println("뱅킹 시스템 프로그램을 종료합니다.");
                break;
            }
            else {
                System.out.println("1번부터 5번 사이의 올바른 메뉴 번호를 입력해주세요.");
            }
        }
        sc.close();
    }
}
