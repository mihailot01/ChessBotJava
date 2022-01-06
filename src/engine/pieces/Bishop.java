package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Board board, boolean color) {
        super(board,"BISHOP", color, 300);
    }
    public Bishop(Board board, int x, int y, boolean color) {
        super(board,"BISHOP",  x, y, color);
        setValue(300);
    }

    public Bishop(Bishop b) {
        super(b);
    }

    @Override
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < diagonalDir.length; i++)
            list.addAll(getMovesDir(b, diagonalDir[i][0], diagonalDir[i][1], true));
        return filterMoves(list);
    }
}
