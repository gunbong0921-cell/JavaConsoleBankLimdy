package banking.step4;

import java.util.Scanner;
import banking.step4.AccountManager;

public class BankingSystemMain {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        while (true) {
            try {
                // "5.계좌정보삭제" 메뉴 추가
                System.out.println("====================Menu====================");
                System.out.println("1.계좌개설, 2.입금, 3.출금, 4.계좌정보출력");
                System.out.println("5.계좌정보삭제, 6.종료");
                System.out.println("============================================");
                System.out.print("선택 : ");
                int choice = sc.nextInt();

                // 1~6 범위를 벗어날 경우 개발자 정의 예외 발생 코드
                if (choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
                    throw new MenuSelectException();
                }

                if (choice == ICustomDefine.EXIT) {
                    System.out.println("****프로그램종료****");
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
                            // 매니저 메서드 호출 시 중복 여부 확인 처리를 위해 스캐너(sc)를 
                        	//함께 넘김.
                            manager.makeAccount(new NormalAccount
                            		(id, name, balance, rate), sc);
                        } 
                        else if (type == 2) {
                            System.out.print("신용등급(A, B, C) : ");
                            String creditStr = sc.next().toUpperCase();
                            int creditRate = 0;

                            if (creditStr.equals("A")) creditRate = 
                            		ICustomDefine.LEVEL_A;
                            else if (creditStr.equals("B")) creditRate = 
                            		ICustomDefine.LEVEL_B;
                            else if (creditStr.equals("C")) creditRate = 
                            		ICustomDefine.LEVEL_C;
                            
                            manager.makeAccount(new HighCreditAccount
                            		(id, name, balance, rate, creditRate, 
                            				creditStr), sc);
                        }
                        break;

                    case ICustomDefine.DEPOSIT:
                        System.out.println("***입 금***");
                        System.out.print("계좌번호와 입금할 금액을 입력하세요\n계좌번호 : ");
                        String depId = sc.next();
                        System.out.print("입금액 : "); 
                        int depMoney = sc.nextInt();
                        try {
                            manager.depositMoney(depId, depMoney);
                        } 
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case ICustomDefine.WITHDRAW:
                        System.out.println("***출 금***");
                        System.out.print("계좌번호와 출금할 금액을 입력하세요\n계좌번호 : ");
                        String witId = sc.next();
                        System.out.print("출금액 : "); 
                        int witMoney = sc.nextInt();
                        manager.withdrawMoney(witId, witMoney, sc);
                        break;

                    case ICustomDefine.INQUIRE:
                        manager.showAllAccountInfo();
                        break;

                    case ICustomDefine.DELETE: // ★ 4단계 신규 추가 메뉴
                        System.out.println("***계좌정보삭제***");
                        System.out.println("삭제할 계좌번호를 입력하세요");
                        System.out.print("계좌번호 : ");
                        String delId = sc.next();
                        manager.deleteAccount(delId);
                        break;
                }

            } 
            catch (MenuSelectException e) {
                System.out.println(e.getMessage());
            } 
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("잘못된 입력 오류입니다. 처음 메뉴로 돌아갑니다.");
                sc.nextLine();
            } 
            catch (Exception e) {
                System.out.println("입력 형식 오류가 발생했습니다.");
                sc.nextLine();
            }
        }
        sc.close();
    }
}