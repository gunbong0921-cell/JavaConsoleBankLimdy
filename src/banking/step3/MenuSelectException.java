package banking.step3;

public class MenuSelectException extends Exception {
    public MenuSelectException() {
        super("메뉴 입력 예외발생.\n메뉴는 1~5사이의 정수를 입력하세요");
    }
    
}
