package engine;

import engine.pieces.Piece;

public class Move {

    public Piece piece;
    public int endX;
    public int endY;

    public Move(Piece piece, int endX, int endY) {
        this.piece = piece;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public String toString() {
        return piece.getName()+" "+piece.getX()+piece.getY()+" "+endX+""+endY;

    }
}
