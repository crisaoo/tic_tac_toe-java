package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import tictactoe.Match;
import tictactoe.TicTacToeException;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Match tictactoe = new Match();

        while(!tictactoe.getGameOver()){
            try{
                UI.clearScreen();
                UI.printMatch(tictactoe);
                UI.readBoardPosition(tictactoe, sc);
            }
            catch(TicTacToeException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }            
        }

        UI.clearScreen();
        UI.printMatch(tictactoe);
        UI.printGameOver(tictactoe.getWinner());
        sc.close();
    }
}