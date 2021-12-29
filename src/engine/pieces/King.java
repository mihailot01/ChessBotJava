package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.List;

public class King extends Piece{


    public King(Board board, boolean color) {
        super(board,"KING", color);
    }

    public King(Board board, int x, int y, boolean color) {
        super(board,"KING", x, y,  color);
        setValue(0);
    }

    @Override
    public List<Move> getAvailableMoves() {

        return null;
    }
}
