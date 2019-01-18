package main;

public abstract class State {
    protected Board board;
    State(Board board){
        this.board = board;
    }
    boolean execute(){
        return false;
        // TODO: write overarching algorithm
    }

    protected int findMatch(int x, int y, int match){
        if(offBoard(x, y)){
            return -1;
        }
        int value = board.board[x][y];
        if(value != 0){
            if(value == match) {
                board.board[x][y] = 0;
            }
            return value;
        }
        else{
            return findMatch(calcX(x), calcY(y), match);
        }
    }

    protected int pullNext(int x, int y){
        if(offBoard(x, y)){
            return -1;
        }
        int value = board.board[x][y];
        if(value != 0){
            board.board[x][y] = 0;
            return value;
        }
        else{
            return pullNext(calcX(x), calcY(y));
        }
    }

    private boolean offBoard(int x, int y){
        if(x < 0 || y < 0 || x >= board.width || y >= board.height){
            return true;
        }
        return false;
    }

    abstract int calcX(int x);
    abstract int calcY(int y);
}
