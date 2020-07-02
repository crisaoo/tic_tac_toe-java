package board;

import tictactoe.TicTacToeException;

public class BoardException extends TicTacToeException {
    private static final long serialVersionUID = 1L;

    public BoardException(String msg){
        super(msg);
    }
}