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

    @Override
    public void setAwardPositions() {
        awardPositions = new int[][]
        {{-70, -60, -40, -30, -30, -40, -60, -70},
        {-60, -45, -35, -25, -25, -35, -45, -60},
        {-50, -30, -20, -20, -20, -20, -30, -50},
        {-50, -20, -10, 0, 0, -10, -20, -50},
        {-50, -20, -10, 0, 0, -10, -20, -50},
        {-50, -30, -20, -20, -20, -20, -30, -50},
        {-60, -45, -35, -25, -25, -35, -45, -60},
        {-70, -60, -40, -30, -30, -40, -60, -70}};
    }
}
