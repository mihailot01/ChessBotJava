package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.List;

public class Rook extends Piece{


    public Rook(Board board, boolean color) {
        super(board, "ROOK", color);
    }
    public Rook(Board board, int x, int y, boolean color) {
        super(board,"ROOK", x, y, color);
    }


    @Override
    public List<Move> getAvailableMoves() {

        return null;
    }
}
