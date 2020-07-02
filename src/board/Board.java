package board;

import tictactoe.SymbolPlayer;

public class Board {
    private SymbolPlayer symbols [][];

    public Board (int row, int col){
        symbols = new SymbolPlayer[row][col];
    }

    public SymbolPlayer [][] getSymbols (){
        return this.symbols;
    }

    public SymbolPlayer getSymbol(int row, int col){
        return symbols[row][col];
    }
    
    public void setSymbol (int row, int col, SymbolPlayer symbol){
        this.symbols[row][col] = symbol;
    }
}