package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(Board board, boolean color) {
        super(board,"KNIGHT", color);
    }

    public Knight(Board board, int x, int y, boolean color) {
        super(board,"KNIGHT", x, y,  color);
        setValue(300);
    }

    public Knight(Knight k){
        super(k);
    }

    @Override
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < gDir.length; i++)
            list.addAll(getMovesDir(b, gDir[i][0], gDir[i][1], false));
        return filterMoves(list);
    }
}
