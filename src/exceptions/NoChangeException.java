package exceptions;

public class NoChangeException extends GameException{
    public NoChangeException() {
        super("That move did not change the board");
    }
}
