package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Board board, boolean color) {
        super(board,"BISHOP", color);
    }
    public Bishop(Board board, int x, int y, boolean color) {
        super(board,"BISHOP",  x, y, color);
    }

    @Override
    public List<Move> getAvailableMoves() {

        return null;
    }
}
