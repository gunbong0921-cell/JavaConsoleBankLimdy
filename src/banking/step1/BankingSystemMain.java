package banking.step1;

import java.util.Scanner;

public class BankingSystemMain {
	
    public static void main(String[] args) {
    	//Scanner sc : 키보드로부터 사용자의 입력을 받기 위한 객체
        Scanner sc = new Scanner(System.in);
        //실제 계좌 개설, 입출금 등의 핵심 비즈니스 로직을 처리해줄 관리자 객체
        AccountManager manager = new AccountManager();
        /*
        무한루프,메뉴화면(while(true))
        1. 사용자가 프로그램종료를 선택하기 전까지 업무를 반복해서 처리하도록
        	while(true)를 사용
        2. 매 반복마다 메뉴를 호출하고, 사용자가 원하는 기능의 번호(choice)를 입력받음	
         */
        while (true) {
            System.out.println("\n-----Menu-----");
            System.out.println("1.계좌개설");
            System.out.println("2.입   금");
            System.out.println("3.출   금");
            System.out.println("4.계좌정보출력");
            System.out.println("5.프로그램종료");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            //조건문을 통한메뉴별기능수행(if, else if)
            /*
            1. 신규계좌개설
            -사용자에게 계좌번호, 고객이름, 초기 입금액을 차례로 입력받음
            -입력받은 값을 manager.makeAccount(id, name, balance)로 넘겨주어
            	실제 계좌 배열에 등록
             */
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
            /*
            2. 입금처리
            -입금 받을 계좌번호와 입금액을 입력받음
            -manager.depositMoney(id, money)를 호출하여 관리자 객체가 해당 계좌를
            	찾아 돈을 차감하도록 명령
             */
            else if (choice == 2) {
            	System.out.println("***입   금***");
                System.out.println("계좌번호와 입금할 금액을 입력하세요.");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("입금액: "); 
                int money = sc.nextInt();
                manager.depositMoney(id, money);
            }
            /*
            3.출금처리
            -출금할 계좌번호와 출금액을 입력받음
            -manager.withdrawMoney(id, money)를 호출하여 관리자 객체가 해당 계좌를
            	찾아 돈을 차감하도록 명령
             */
            else if (choice == 3) {
            	System.out.println("***출   금***");
                System.out.println("계좌번호와 출금할 금액을 입력하세요.");
                System.out.print("계좌번호: "); 
                String id = sc.next();
                System.out.print("출금액: "); 
                int money = sc.nextInt();
                manager.withdrawMoney(id, money);
            } 
            /*
            4.전체계좌정보출력
            -추가적인 입력 없이 manager.showAllAccountInfo()를 즉시 호출하여 현재까지
            	개설된 모든 계좌의 정보를 화면에 출력
             */
            else if (choice == 4) {
                manager.showAllAccountInfo();
            } 
            /*
            5.프로그램종료
            -안내메시지 출력 후 break를 통해 while 무한루프를 탈출
            -루프 탈출 후 sc.close()가 실행되어 입력 스트림을 닫으면 프로그램이 최종 종료됨
             */
            else if (choice == 5) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
        sc.close();
    }
    
}
