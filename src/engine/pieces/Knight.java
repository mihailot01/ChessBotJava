package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.List;

public class Knight extends Piece{

    public Knight(Board board, boolean color) {
        super(board,"KNIGHT", color);
    }

    public Knight(Board board, int x, int y, boolean color) {
        super(board,"KNIGHT", x, y,  color);
        setValue(300);
    }

    @Override
    public List<Move> getAvailableMoves() {

        return null;
    }
}
