package tictactoe;

public class TicTacToeException extends RuntimeException{  
    private static final long serialVersionUID = 1L;

    public TicTacToeException (String msg){
        super(msg);
    }
}