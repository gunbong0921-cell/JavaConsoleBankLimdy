package banking.step4;

public interface ICustomDefine {
	//github연동
	//연동 완료 후 커밋&푸시
    int MAKE = 1; //계좌개설
    int DEPOSIT = 2; //입금
    int WITHDRAW = 3; //출금
    int INQUIRE = 4; //계좌정보출력
    int DELETE = 5; //계좌정보삭제 (4단계 추가)
    int EXIT = 6; //종료 

    int LEVEL_A = 7; //이자율
    int LEVEL_B = 4; //이자율
    int LEVEL_C = 2; //이자율
}
