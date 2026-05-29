package banking.step2;

public interface ICustomDefine {
	
    int MAKE = 1; //계좌개설
    int DEPOSIT = 2; //입금
    int WITHDRAW = 3; //출금
    int INQUIRE = 4; //전체계좌정보출력
    int EXIT = 5; //프로그램종료

    int LEVEL_A = 7; //A등급우대이율 7%
    int LEVEL_B = 4; //B등급우대이율 4%
    int LEVEL_C = 2; //C등급우대이율 2%
}
