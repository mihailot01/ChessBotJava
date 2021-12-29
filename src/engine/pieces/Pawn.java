package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(Board board, boolean color) {
        super(board,"PAWN", color);
    }
    public Pawn(Board board, int x, int y, boolean color) {
        super(board,"PAWN", x, y, color);
        setValue(100);
    }

    public Pawn(Pawn p){
        super(p);
    }

    @Override
    public List<Move> getAvailableMoves(Board b) {
        return null;
    }
}
