package main;

public class MoveUp extends State {
    MoveUp(Board board){
        super(board);
    }

    @Override
    protected boolean execute() {
        boolean change = false;

        // Iterate through columns
        for(int x = 0; x < board.width; x++){
            // First, combine like values moving down
            for(int y = 0; y < board.height; y++){
                int value = board.board[x][y];
                if(value != 0){
                    int next = findMatch(calcX(x), calcY(y), value);
                    if(next == value){
                        board.board[x][y] = value * 2;
                        change = true;
                    }
                }
            }

            // Next, slide values up
            for(int y = 0; y < board.height; y++){
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
        return x;
    }

    @Override
    int calcY(int y) {
        // Start from top and move down
        return y + 1;
    }
}
