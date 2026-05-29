package banking.step3;

import java.util.Scanner;
import banking.step3.AccountManager;

public class BankingSystemMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        while (true) {
            try {
                System.out.println("==================Menu==================");
                System.out.println("1.계좌개설, 2.입금, 3.출금, 4.계좌정보출력, 5.종료");
                System.out.println("========================================");
                System.out.print("선택 : ");
                int choice = sc.nextInt();

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
            catch (Exception e) {
                System.out.println("잘못된 입력 오류입니다. 처음 메뉴로 돌아갑니다.");
                sc.nextLine(); // 버퍼 비우기
            }
        }
        sc.close();
    }
}
