package engine;

import engine.pieces.Piece;

public class Move implements Comparable<Move>{

    private Piece piece;
    private int endX;
    private int endY;
    private int startX;
    private int startY;

    private Piece capturedPiece;
    private boolean oldMoved = false;

    private boolean captures;

    private Move castleMove1;
    private Move castleMove2;


    public Move(Piece piece, int endX, int endY) {
        this.piece = piece;
        this.startX = piece.getX();
        this.startY = piece.getY();
        this.endX = endX;
        this.endY = endY;
        this.captures = piece.getBoard().enemyPiece(piece,endX,endY);
        this.capturedPiece = piece.getBoard().getPiece(endX,endY);
        this.castleMove1 = null;
        this.castleMove2 = null;
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


    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public boolean isOldMoved() {
        return oldMoved;
    }

    public void setOldMoved(boolean oldMoved) {
        this.oldMoved = oldMoved;
    }

    public boolean isCaptures() {
        return captures;
    }

    public void setCaptures(boolean captures) {
        this.captures = captures;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public Move getCastleMove1() {
        return castleMove1;
    }

    public void setCastleMove1(Move castleMove) {
        this.castleMove1 = castleMove;
    }

    public Move getCastleMove2() {
        return castleMove2;
    }

    public void setCastleMove2(Move castleMove2) {
        this.castleMove2 = castleMove2;
        castleMove2.setCastleMove1(this);
    }
}
