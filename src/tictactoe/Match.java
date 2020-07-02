package tictactoe;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Position;

public class Match {
    private Board board;
    private SymbolPlayer currentPlayer;
    private int turn;
    private boolean gameOver;
    private SymbolPlayer winner;
    private List <Position> winnerSequence;

    public Match (){
        board = new Board(3,3);
        turn = 1;
        currentPlayer = SymbolPlayer.X;
        winnerSequence = new ArrayList<>();
    }

    // -------------------------------------- GETTER METHODS
    public Board getBoard(){
        return this.board;
    }
    
    public SymbolPlayer getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public int getTurn(){
        return this.turn;
    }

    public boolean getGameOver(){
        return this.gameOver;
    }

    public SymbolPlayer getWinner(){
        return this.winner;
    }

    public List <Position> getWinnerSequence(){
        return this.winnerSequence;
    }
    // ----------------------------------------------------------------
    
    
    // -------------------------------------- MOVE
    public void performTicTacToeMove (BoardPosition boardPosition){
        Position position = boardPosition.toPosition();
        makeMove(currentPlayer, position);

        if (checkWin(currentPlayer)){
            gameOver = true;
            winner = currentPlayer;
            winnerSequence(position);
        }
        else
            gameOver = checkWeTied(opponent(currentPlayer));

        nextTurn();
    }


    private void makeMove (SymbolPlayer player, Position position){
        if (!isEmpty(position))
            throw new TicTacToeException("This position is already occupied.");

        board.setSymbol(position.getRow(), position.getCol(), player);
    }

    private void undoMove (Position position){
        if (!isEmpty(position))
            board.setSymbol(position.getRow(), position.getCol(), null);
    }


    public boolean isEmpty (Position position){
        return board.getSymbol(position.getRow(), position.getCol()) == null;
    }


    private void nextTurn(){
        this.turn++;
        changePlayer();
    }

    private void changePlayer(){
        this.currentPlayer = (currentPlayer.equals(SymbolPlayer.O)) ? SymbolPlayer.X : SymbolPlayer.O;
    }
    // -------------------------------------------

    // -------------------------------------- WIN OR WE TIED CHECK
    private boolean checkWin (SymbolPlayer symbol){
        SymbolPlayer [][] sym = board.getSymbols();        
        return((sym[0][0] == symbol && sym[0][1] == symbol && sym[0][2] == symbol) ||
               (sym[1][0] == symbol && sym[1][1] == symbol && sym[1][2] == symbol) ||
               (sym[2][0] == symbol && sym[2][1] == symbol && sym[2][2] == symbol) ||
               (sym[0][0] == symbol && sym[1][0] == symbol && sym[2][0] == symbol) ||
               (sym[0][1] == symbol && sym[1][1] == symbol && sym[2][1] == symbol) ||
               (sym[0][2] == symbol && sym[1][2] == symbol && sym[2][2] == symbol) ||
               (sym[0][0] == symbol && sym[1][1] == symbol && sym[2][2] == symbol) ||
               (sym[0][2] == symbol && sym[1][1] == symbol && sym[2][0] == symbol));
    }

    private boolean checkWeTied(SymbolPlayer opponent){
        List <Position> emptySymbols = emptySymbols(board.getSymbols());
        boolean win = false;

        for (Position position : emptySymbols){
            makeMove(opponent, position);
            win = checkWin(opponent);
            
            if (win)
                break;
        }

        for (Position position : emptySymbols)
            undoMove(position);

        if (!win && emptySymbols.size() > 1)
            win = !checkWeTied();

        return !win;
    }

    private boolean checkWeTied(){
        List <Position> emptySymbols = emptySymbols(board.getSymbols());
        boolean win = false;

        for (Position position : emptySymbols){
            makeMove(currentPlayer, position);
            win = checkWin(currentPlayer);
            
            if (win)
                break;
        }

        for (Position position : emptySymbols)
            undoMove(position);

        return !win;
    }

    private List <Position> emptySymbols (SymbolPlayer [][] symbols){
        List <Position> emptySymbols = new ArrayList<>();
        
        for (int i =0; i < symbols.length; i++)
            for (int j =0; j < symbols.length; j++)
                if (isEmpty(new Position(i,j))){
                    emptySymbols.add(new Position(i,j));
                }        
        
        return emptySymbols;
    }

    private SymbolPlayer opponent (SymbolPlayer symbol){
        return (symbol == SymbolPlayer.X)? SymbolPlayer.O : SymbolPlayer.X;
    }


    private int winnerSequence (Position p){   
        for (int row=0 ; row < board.getSymbols().length; row++){
            if (checkPosition(new Position(row, p.getCol()), winner))
                winnerSequence.add(new Position(row, p.getCol()));
            else break;
        }

        if (winnerSequence.size() == 3)
            return 0;
        winnerSequence.clear();


        for (int col=0 ; col < board.getSymbols().length; col++){
            if (checkPosition(new Position(p.getRow(), col), winner))
                winnerSequence.add(new Position(p.getRow(), col));
            else break;
        }

        if (winnerSequence.size() == 3)
            return 0;
        winnerSequence.clear();


        for (int diag = 0; diag < board.getSymbols().length; diag++){
            if (checkPosition(new Position(diag, diag), winner))
                winnerSequence.add(new Position(diag, diag));
            else break;
        }

        if (winnerSequence.size() == 3)
            return 0;
        winnerSequence.clear();
        

        for (int i = 0, j= 2; i < board.getSymbols().length; i++, j--){
            if (checkPosition(new Position(i, j), winner))
               winnerSequence.add(new Position(i, j));
            else break;
        }
        
        return 0;
    }

    private boolean checkPosition (Position p, SymbolPlayer symbol){
        return board.getSymbol(p.getRow(), p.getCol()) == symbol;
    }
    // -----------------------------------------------------------
}