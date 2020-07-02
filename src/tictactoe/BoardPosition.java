package tictactoe;

import board.Position;

public class BoardPosition {
    private char col;
    private int row;

    public BoardPosition (char col, int row){
        if (col > 'c' || col < 'a' || row > 3 || row < 1)
            throw new TicTacToeException("The position be must from a1 to c3.");

        this.col = col;
        this.row = row;
    }

    public char getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }


    public Position toPosition (){
        String aid = "abc";
        return new Position(row - 1, aid.indexOf(col));
    }

    @Override
    public String toString(){
        return "" + col + row;
    }
}