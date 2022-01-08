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
        if(this.captures && o.captures) {
            if(this.piece.getValue() - this.capturedPiece.getValue() > o.piece.getValue() - o.capturedPiece.getValue()) return 1;
            return -1;
        }
        if(this.captures) return 1;
        return -1;
    }
}
