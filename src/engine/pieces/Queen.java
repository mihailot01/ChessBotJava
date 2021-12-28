package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.List;

public class Queen extends Piece{

    public Queen(Board board, boolean color) {
        super(board,"QUEEN", color);
    }
    public Queen(Board board, int x, int y, boolean color) {
        super(board,"QUEEN", x, y,  color);
    }

    @Override
    public List<Move> getAvailableMoves() {

        return null;
    }
}
