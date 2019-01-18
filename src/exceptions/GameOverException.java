package exceptions;

public class GameOverException extends GameException{
    public GameOverException(){
        super("The game is over");
    }
}
