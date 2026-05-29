package banking.step1;

import java.util.Scanner;

public class BankingSystemMain {
	//1
	//2-1
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

            if (choice == 1) {
            	System.out.println("***신규계좌개설***");
                System.out.println("-------------");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("고객이름: "); 
                String name = sc.next();
                System.out.print("입금액: "); 
                int balance = sc.nextInt();
                manager.makeAccount(id, name, balance);
            } 
            else if (choice == 2) {
            	System.out.println("***입   금***");
                System.out.println("계좌번호와 입금할 금액을 입력하세요.");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("입금액: "); 
                int money = sc.nextInt();
                manager.depositMoney(id, money);
            } 
            else if (choice == 3) {
            	System.out.println("***출   금***");
                System.out.println("계좌번호와 출금할 금액을 입력하세요.");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("출금액: "); 
                int money = sc.nextInt();
                manager.withdrawMoney(id, money);
            } 
            else if (choice == 4) {
                manager.showAllAccountInfo();
            } 
            else if (choice == 5) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
        sc.close();
    }
    
}
