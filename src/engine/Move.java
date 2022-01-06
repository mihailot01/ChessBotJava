package engine;

import engine.pieces.Piece;

public class Move implements Comparable<Move>{

    public Piece piece;
    public int endX;
    public int endY;
    public int startX;
    public int startY;

    public Piece capturedPiece = null;
    public boolean oldMoved = false;

    public boolean captures;

    public Move(Piece piece, int endX, int endY) {
        this.piece = piece;
        this.endX = endX;
        this.endY = endY;
        this.captures = piece.getBoard().enemyPiece(piece,endX,endY);
    }

    @Override
    public String toString() {
        return piece.getName()+" "+piece.getX()+piece.getY()+" "+endX+""+endY;

    }

    @Override
    public int compareTo(Move o) {
        if(this.equals(o) || this.captures == o.captures)
            return 0;
        if(this.captures)
            return 1;
        return -1;
    }
}
