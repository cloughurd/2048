package main;

public class MoveDown extends State {
    MoveDown(Board board){
        super(board);
    }

    @Override
    protected boolean execute() {
        boolean change = false;

        // Iterate through columns
        for(int x = 0; x < board.width; x++){
            // First, combine like values moving up
            for(int y = board.height - 1; y >= 0; y--){
                int value = board.board[x][y];
                if(value != 0){
                    int next = findMatch(calcX(x), calcY(y), value);
                    if(next == value){
                        board.board[x][y] = value * 2;
                        change = true;
                    }
                }
            }

            // Next, slide values down
            for(int y = board.height - 1; y >= 0; y--){
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
        // Start from bottom and move up
        return y - 1;
    }
}
