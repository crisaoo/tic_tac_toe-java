package board;

import tictactoe.BoardPosition;

public class Position {
    private Integer row;
    private Integer col;

    public Position(int row, int col ){
        if (row > 2 || row < 0 || col > 2 || col < 0 )
            throw new BoardException("The position be must from 0, 0 to 2, 2.");
            
        this.row = row;
        this.col = col;
    }


    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }


    public BoardPosition toBoardPosition (){
        String aid = "abc";
        return new BoardPosition(aid.charAt(col), row +1);
    }

    @Override
    public String toString(){
        return  row + ", " + col;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + ((col == null) ? 0 : col.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (row != other.row)
            return false;
        if (col == null) {
            if (other.col != null)
                return false;
        } else if (!col.equals(other.col))
            return false;
        return true;
    }
}
