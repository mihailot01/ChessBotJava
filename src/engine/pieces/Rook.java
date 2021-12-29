package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{


    public Rook(Board board, boolean color) {
        super(board, "ROOK", color);
    }
    public Rook(Board board, int x, int y, boolean color) {
        super(board,"ROOK", x, y, color);
        setValue(500);
    }


    @Override
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < ortogonalDir.length; i++)
            list.addAll(getMovesDir(b, ortogonalDir[i][0], ortogonalDir[i][1], true));
        return list;
    }
}
