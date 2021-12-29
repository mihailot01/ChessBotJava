package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
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
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < nextDir.length; i++)
            list.addAll(getMovesDir(b, nextDir[i][0], nextDir[i][1], false));
        return list;
    }
}
