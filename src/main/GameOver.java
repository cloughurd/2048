package main;

public class GameOver extends State {
    GameOver(Board board){
        super(board);
    }

    @Override
    boolean execute() {
        return false;
    }

    @Override
    int calcX(int x) {
        return -1;
    }

    @Override
    int calcY(int y) {
        return -1;
    }
}
