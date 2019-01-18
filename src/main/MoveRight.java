package main;

public class MoveRight extends State {
    MoveRight(Board board){
        super(board);
    }

    @Override
    protected boolean execute() {
        boolean change = false;

        // Iterate through rows
        for(int y = 0; y < board.height; y++){
            // First, combine like values going left
            for(int x = board.width - 1; x >= 0; x--){
                int value = board.board[x][y];
                if(value != 0){
                    int next = findMatch(calcX(x), calcY(y), value);
                    if(next == value){
                        board.board[x][y] = value * 2;
                        change = true;
                    }
                }
            }

            // Next, slide values right
            for(int x = board.width - 1; x >= 0; x--){
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
        // Starts from right edge and move left
        return x - 1;
    }

    @Override
    int calcY(int y) {
        return y;
    }
}
