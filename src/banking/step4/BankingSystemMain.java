package banking.step4;

import java.util.Scanner;
import banking.step4.AccountManager;

public class BankingSystemMain {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        while (true) {
            try {
            	/*
            	신규메뉴추가와 상수범위확장
            	1.메뉴재배치
            	기존 5번에 위치한 프로그램 종료(EXIT)가 6번으로 내려가고 5번에 DELETE
            	(계좌정보삭제)메뉴가 추가됨
            	2.유연한 검증
            	메뉴가 늘어남에 따라 예외를 던지는 기준도 자동으로 연동됨. ICustomerDefine.EXIT
            	가 내부적으로 6으로 변경었으므로 1~6을 벗어난 정수 입력하면 오류메시지를 출력함
            	 */
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
                        /*
                        계좌개설시 스캐너분출(case MAKE)
                        -콘솔창에서 사용자의 답변을 입력받아야 하므로 메인 클래스가 관리하던 키보드
                        압력 객체인 Scanner sc를 매개변수로 함께 넘겨주어 매니저 내부에 키보드
                        입력을 이어받을 수 있도록 브릿지를 놔줌
                         */
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
                    /*
                    계좌정보삭제분기(case DELETE)
                    -사용자가 5번 메뉴를 입력하면 출력하는 분기점
                    -제거하고 싶은 계좌번호(delId)를 타이핑 받아 manager.deleteAccount(delId)
                    로 원격명령을 하달. 내부적으로 HashSet 컬렉션 덕분에 데이터가 삭제 및 정돈됨    
                     */
                    case ICustomDefine.DELETE: // ★ 4단계 신규 추가 메뉴
                        System.out.println("***계좌정보삭제***");
                        System.out.println("삭제할 계좌번호를 입력하세요");
                        System.out.print("계좌번호 : ");
                        String delId = sc.next();
                        manager.deleteAccount(delId);
                        break;
                }

            } 
            /*
            세분화된 다중Catch블록구보(Multi-catch)
            -발생할 수 있는 에러의 성격에 맞춰 catch를 배치
            -메뉴범위이탈, 문자열가공에러, 데이터오입력(Exception)등을 계단식으로 걸러내어 
            에러 발생시 sc.nextLine()을 수행해 키보드 버퍼를 깔끔히 청소함으로써 프로그램이
            무한루프에 빠지 않도록함
             */
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