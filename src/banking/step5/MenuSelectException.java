package banking.step5;

public class MenuSelectException extends Exception {
    public MenuSelectException() {
        super("메뉴 입력 예외발생.\n메뉴는 1~6사이의 정수를 입력하세요");
    }
    
}
