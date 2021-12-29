package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(Board board, boolean color) {
        super(board,"QUEEN", color);
    }
    public Queen(Board board, int x, int y, boolean color) {
        super(board,"QUEEN", x, y,  color);
        setValue(900);
    }

    @Override
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < diagonalDir.length; i++)
            list.addAll(getMovesDir(b, diagonalDir[i][0], diagonalDir[i][1], true));
        for(int i = 0; i < ortogonalDir.length; i++)
            list.addAll(getMovesDir(b, ortogonalDir[i][0], ortogonalDir[i][1], true));
        return list;
    }
}
