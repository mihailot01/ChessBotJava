package engine;

import engine.pieces.Piece;

public class Move {

    Piece piece;
    int endX;
    int endY;

    public Move(Piece piece, int endX, int endY) {
        this.piece = piece;
        this.endX = endX;
        this.endY = endY;
    }
}
