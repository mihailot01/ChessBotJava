package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{


    public Rook(Board board, boolean color) {
        super(board, "ROOK", color,500);
    }
    public Rook(Board board, int x, int y, boolean color) {
        super(board,"ROOK", x, y, color);
        setValue(500);
    }

    public Rook(Rook r){
        super(r);
    }


    @Override
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < ortogonalDir.length; i++)
            list.addAll(getMovesDir(b, ortogonalDir[i][0], ortogonalDir[i][1], true));
        return filterMoves(list);
    }

    @Override
    public void setAwardPositions() {
        awardPositions = new int[][]
        {{-30,-15,0,0,0,0,-15,-30},
        {-40,-20,-20,-20,-20,-20,-20,-40},
        {-40,-20,-20,-20,-20,-20,-20,-40},
        {-40,-20,-20,-20,-20,-20,-20,-40},
        {-40,-20,-20,-20,-20,-20,-20,-40},
        {-40,-20,-20,-20,-20,-20,-20,-40},
        {-40,-20,-20,-20,-20,-20,-20,-40},
        {-30, -15, -5, 0, 0, -5, -15, 0, -30}};
    }
}
