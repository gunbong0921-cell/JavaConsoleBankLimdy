package banking.step3;
/*
예외클래스 상속(extends Exception)
-throw 키워드로 던질 수 있는 진짜 예외클래스로 인정받으려면 반드시 Throwable이나 
	Exception계열을 상속받아야만 함. 상속을 통해 MenuSelectException은 예외처리
	매커니즘(try-catch-throw)에 완벽히 편입됨 
 */
public class MenuSelectException extends Exception {
	/*
	기본생성자와 부모생성자 호출(super)
	-나중에 이 예외를 붙잡은 곳(BankingSystemMain등)에서 e.getMessage()를 호출
	하면 설정한 메시지가 반환됨
	 */
    public MenuSelectException() {
        super("메뉴 입력 예외발생.\n메뉴는 1~5사이의 정수를 입력하세요");
    }
    
}
