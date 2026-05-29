package banking.step3;

import java.util.Scanner;
import banking.step3.AccountManager;

public class BankingSystemMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();
        /*
        사용자정의 예외와 메뉴검증(MenuSelectException)
        -사용자가 메뉴에 없는 번호를 입력했을때를 대비한 검증 로직
        -개발자 정의 예외클래스(MenuSelectException)를 생성하여 강제로 던짐(throw)
        -던져진 예외는 루프 맨 아래에 있는 catch(MenuSelectException e)블록에
        	붙잡아 "메뉴 입력 예외발생"과 같은 안내 문구를 띄우고 프로그램 종료없이 메뉴
        	재입력 단계로 건너뛰게 만듬
         */
        while (true) {
            try {
                System.out.println("==================Menu==================");
                System.out.println("1.계좌개설, 2.입금, 3.출금, 4.계좌정보출력, 5.종료");
                System.out.println("========================================");
                System.out.print("선택 : ");
                int choice = sc.nextInt();
                
                /*
                제어흐름의 최적화(swich~case)와 if의 조화)
                -2단계까지는 복잡한 if-else문으로 코드를 채웠으나 3단계에서 직관적인
                	switch-case문으로 리팩토링함
                -단, 종료(EXIT)메뉴는 switch내부에서 break를 쓰면 루프 탈출이 아닌 switch
                	문 탈출이 되기 때문에 switch에 진입하기 전 if문으로 낚아채서 안전하게 while
                	무한루프를 탈출(break)하도록 설계
                 */
                // 메뉴 예외 검증 (1~5 범위 이탈 시 개발자 정의 예외 던짐)
                if (choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
                    throw new MenuSelectException();
                }

                if (choice == ICustomDefine.EXIT) {
                    System.out.println("***프로그램종료***");
                    break;
                }

                switch (choice) {
                    case ICustomDefine.MAKE:
                        System.out.println("***계좌개설***");
                        System.out.println("-----계좌선택-----");
                        System.out.println("1.보통계좌");
                        System.out.println("2.신용신뢰계좌");
                        System.out.print("선택 : ");
                        int type = sc.nextInt();

                        System.out.print("계좌번호 : "); 
                        String id = sc.next();
                        System.out.print("고객이름 : "); 
                        String name = sc.next();
                        System.out.print("잔고 : "); 
                        int balance = sc.nextInt();
                        System.out.print("기본이자%(정수형태) : "); 
                        int rate = sc.nextInt();

                        if (type == 1) {
                            manager.makeAccount(new NormalAccount(id, name, balance, rate));
                        } 
                        else if (type == 2) {
                            System.out.print("신용등급(A, B, C) : ");
                            String creditStr = sc.next().toUpperCase();
                            int creditRate = 0;

                            if (creditStr.equals("A")) creditRate = ICustomDefine.LEVEL_A;
                            else if (creditStr.equals("B")) creditRate = ICustomDefine.LEVEL_B;
                            else if (creditStr.equals("C")) creditRate = ICustomDefine.LEVEL_C;
                            
                            manager.makeAccount(new HighCreditAccount
                            		(id, name, balance, rate, creditRate, creditStr));
                        }
                        break;

                    case ICustomDefine.DEPOSIT:
                        System.out.println("***입 금***");
                        System.out.print("계좌번호와 입금할 금액을 입력하세요\n계좌번호 : ");
                        String depId = sc.next();
                        System.out.print("입금액 : ");
                        int depMoney = sc.nextInt();
                        /*
                        입금예외처리연계(case DEPOSIT)
                        -AccountManager의depositMoney메서드는 음수 입금이나 500원 단위가 아닐때 
                        	예외를 상위로 던지도록(throws)설계함
                        -메인 클래스에서 try-catch로 감싸서, 매니저가 던진 해당 메세지(e.getMessage())
                        	를 받아서 화면에 출력 	 
                         */
                        try {
                            manager.depositMoney(depId, depMoney);
                        } 
                        catch (Exception e) {
                            // 영상 속 음수 및 500원 단위 입력 차단 에러 문구 가동
                            System.out.println(e.getMessage());
                        }
                        break;

                    case ICustomDefine.WITHDRAW:
                        System.out.println("***출 금***");
                        System.out.print("계좌번호와 출금할 금액을 입력하세요\n계좌번호 : ");
                        String witId = sc.next();
                        System.out.print("출금액 : ");
                        int witMoney = sc.nextInt();
                        /*
                        출금(case WITHDRAW)
                        -매니저 내부에 사용자의 추가의사 결정(잔액부족시 전액출금)을 물어보고 입력받아
                        야하므로 현재 메인에서 사용하는 Scanner sc객체를 매개변수로 함께 넘겨주는
                        구조로 유연하게 연동됨
                         */
                        // 내부에서 잔액부족 예외 확인 트래킹을 수행하기 위해 스캐너 객체 동시 전달
                        manager.withdrawMoney(witId, witMoney, sc);
                        break;

                    case ICustomDefine.INQUIRE:
                        manager.showAllAccountInfo();
                        break;
                }
            } 
            catch (MenuSelectException e) {
                // 영상 속 "선택: 99 -> 메뉴 입력 예외발생." 화면 구현
                System.out.println(e.getMessage());
            } 
            /*
            데이터입력오류 방어(catch(Exception))
            -사용자가 숫자입력 코너에서 실수로 문자를 입력했을 시 발생하는 오류(InputMismatchException등)
            	를 방어하는 최종 보루
            -Exception e로 전부 무차별수용(Catch)한 뒤 잘못 입력되어 버퍼에 남아있는 문자열을
            	sc.nextLine();으로 깨끗이 삭제. 해당 처리가 없다면 프로그램은 무한루프됨
             */
            catch (Exception e) {
                System.out.println("잘못된 입력 오류입니다. 처음 메뉴로 돌아갑니다.");
                sc.nextLine(); // 버퍼 비우기
            }
        }
        sc.close();
    }
}
