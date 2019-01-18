package controller;

import exceptions.GameException;
import main.Board;
import main.MoveDirection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLineOutput {
    private Board board;
    public CommandLineOutput(){
        board = new Board();
    }
    public void run(){
        while(true){
            System.out.println(board.toString());
            MoveDirection direction = getMoveInput();
            try {
                board.move(direction);
            }
            catch (GameException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private MoveDirection getMoveInput() {
        System.out.print("Which direction would you like to move? (WASD: W=up,A=left,S=down,D=right) ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String text = in.readLine();
            switch (text) {
                case "W": case "w":
                    return MoveDirection.Up;
                case "A": case "a":
                    return MoveDirection.Left;
                case "S": case "s":
                    return MoveDirection.Down;
                case "D": case "d":
                    return MoveDirection.Right;
                default:
                    System.out.println("Invalid input!");
                    return getMoveInput();
            }
        } catch (Exception e) {
            System.out.println("Exception thrown in get move input, try again (check input): ");
            return getMoveInput();
        }
    }

    public static void main(String[] args){
        new CommandLineOutput().run();
    }
}
