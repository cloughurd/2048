package main;

import exceptions.GameException;
import exceptions.GameOverException;
import exceptions.NoChangeException;

public class Board {
    final int height = 4;
    final int width = 4;
    int[][] board = new int[width][height];
    private State currentState;

    /*
    Board orientation:
    | [0][0] | [1][0] | [2][0] | [3][0] |
    | [0][1] | [1][1] | [2][1] | [3][1] |
    | [0][2] | [1][2] | [2][2] | [3][2] |
    | [0][3] | [1][3] | [2][3] | [3][3] |
     */

    public Board(){
        // Fill board will 0's
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                board[x][y] = 0;
            }
        }
        // Add two starting numbers to the board
        generateNewNumber();
        generateNewNumber();
    }

    public void move(MoveDirection direction) throws GameException {
        if(currentState instanceof GameOver){
            throw new GameOverException();
        }
        // If the game isn't over, set the state to reflect the desired move and execute
        setState(direction);
        boolean valid = currentState.execute();
        // If valid, add number to board
        if(valid){
            generateNewNumber();
        }
        else{
            throw new NoChangeException();
        }
        if(gameOver()){
            setState(MoveDirection.None);
            throw new GameOverException();
        }
    }

    private void generateNewNumber(){
        int randomRange = 4;
        while(true) {
            // Pick random spot on the board
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            if(board[x][y] == 0){
                int num = (int)(Math.random() * randomRange); // num will be set between 0 and randomRange
                // In only one case, num will be set to 4. Otherwise, it'll be set to 2. Results in 1/randomRange chance of being a 4
                if(num < randomRange - 1){
                    num = 2;
                }
                else{
                    num = 4;
                }
                // Insert the new number into the board
                board[x][y] = num;
                // Break since a new number was generated and added to the board
                break;
            }
            // If random spot chosen was not a 0, keep trying
        }
    }

    private void setState(MoveDirection direction){
        switch (direction){
            case Right:
                currentState = new MoveRight(this);
                break;
            case Left:
                currentState = new MoveLeft(this);
                break;
            case Down:
                currentState = new MoveDown(this);
                break;
            case Up:
                currentState = new MoveUp(this);
                break;
            default:
                currentState = new GameOver(this);
        }
    }

    /**
     * Checks to see any elements on the board are 0 or can be combined with their neighbor
     * @return false if game can still be played, true if game is over
     */
    private boolean gameOver(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int value = board[x][y];
                // Check if this space is 0
                if(value == 0){
                    // Return false means game isn't over
                    return false;
                }
                // Check if values around it could combine or are 0
                if(isEqualOrZero(x + 1, y, value) || isEqualOrZero(x - 1, y, value) || isEqualOrZero(x, y + 1, value) || isEqualOrZero(x, y - 1, value)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isEqualOrZero(int x, int y, int value){
        if(currentState.offBoard(x,y)){
            return false;
        }
        else if(board[x][y] == value || board[x][y] == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String divider = "_____________________\n";
        builder.append(divider);
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                builder.append(String.format("|%4d|",board[x][y]));
            }
            builder.append("\n");
            builder.append(divider);
        }
        return builder.toString();
    }
}
