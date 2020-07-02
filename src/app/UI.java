package app;

import java.util.List;
import java.util.Scanner;

import board.Position;
import tictactoe.Match;
import tictactoe.SymbolPlayer;

public class UI {
    public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";


    public static void printMatch(Match tictactoe){
        System.out.println("Turn " + tictactoe.getTurn() + "\n");

        if (tictactoe.getGameOver())
            printSymbols(tictactoe.getBoard().getSymbols(), tictactoe.getWinnerSequence());
        else{
            printSymbols(tictactoe.getBoard().getSymbols());
            System.out.println("\nWaiting Player: " + tictactoe.getCurrentPlayer());
        }
    }

    public static void printSymbols (SymbolPlayer [][] symbols){
        for (int i = 0; i < symbols.length; i++){
            System.out.print(PURPLE + (i+1) + RESET + " ");

            if (symbols[i][0] != null)
                System.out.print(symbols[i][0]);
            else 
                System.out.print(" ");

            for (int  j = 1; j < symbols[i].length; j++){
                System.out.print("|");
                if(symbols[i][j] != null)
                    System.out.print(symbols[i][j]);
                else 
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(PURPLE + "  a b c" + RESET);
    }

    public static void printSymbols (SymbolPlayer [][] symbols, List<Position> winnerSequence){
        for (int i = 0; i < symbols.length; i++){                                               
            System.out.print(PURPLE + (i+1) + RESET + " ");

            if(winnerSequence.contains(new Position(i, 0)))
                System.out.print(RED + symbols[i][0] + RESET);
            else if (symbols[i][0] != null)
                System.out.print(symbols[i][0]);
            else 
                System.out.print(" ");

            for (int  j = 1; j < symbols[i].length; j++){
                System.out.print("|");
                if(winnerSequence.contains(new Position(i, j)))
                    System.out.print(RED + symbols[i][j] + RESET);
                else if(symbols[i][j] != null)
                    System.out.print(symbols[i][j]);
                else 
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(PURPLE + "  a b c" + RESET);
    }

    public static void printGameOver (SymbolPlayer winner){
        System.out.println();
        if(winner != null)
            System.out.println(GREEN + "Player " + winner.toString() + " is the Winner!!" + RESET);
        else
            System.out.println(YELLOW + "Oh, We Tied!!" + RESET);
    }

    public static void readBoardPosition(Match tictactoe, Scanner sc){
        System.out.print("Position: ");
        String boardPosition = sc.nextLine();
        char col = boardPosition.toLowerCase().charAt(0);
        int row = Integer.parseInt(boardPosition.substring(1));

        tictactoe.performTicTacToeMove(new tictactoe.BoardPosition(col, row));
    }
    
    public static void clearScreen (){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}