package main;

public class MoveLeft extends State{
    MoveLeft(Board board){
        super(board);
    }

    @Override
    protected boolean execute() {
        boolean change = false;

        // Iterate through rows
        for(int y = 0; y < board.height; y++){
            // First, combine like values going right
            for(int x = 0; x < board.width; x++){
                int value = board.board[x][y];
                if(value != 0){
                    int next = findMatch(calcX(x), calcY(y), value);
                    if(next == value){
                        board.board[x][y] = value * 2;
                        change = true;
                    }
                }
            }

            // Next, slide values left
            for(int x = 0; x < board.width; x++){
                int value = board.board[x][y];
                if(value == 0){
                    int next = pullNext(calcX(x), calcY(y));
                    if(next > 0){
                        board.board[x][y] = next;
                        change = true;
                    }
                }
            }
        }

        return change;
    }

    @Override
    int calcX(int x) {
        // Start from left edge and move right
        return x + 1;
    }

    @Override
    int calcY(int y) {
        return y;
    }
}
