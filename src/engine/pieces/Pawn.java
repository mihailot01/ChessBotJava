package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(Board board, boolean color) {
        super(board,"PAWN", color,100);
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
        List<Move> moves = new ArrayList<>();
        int dir = color?-1:1;
        if(this.board.freeSquare(this.x+dir,this.y)) {
            moves.add(new Move(this, this.x + dir, this.y));
            if (!this.moved && this.board.freeSquare(this.x + 2 * dir, this.y))
                moves.add(new Move(this, this.x + 2 * dir, this.y));
        }
        if(this.board.enemyPiece(this,this.x+dir,this.y-1))
            moves.add(new Move(this,this.x+dir,this.y-1));
        if(this.board.enemyPiece(this,this.x+dir,this.y+1))
            moves.add(new Move(this,this.x+dir,this.y+1));
        return filterMoves(moves);
    }

    @Override
    public void setAwardPositions() {
        awardPositions = new int[][]
        {{-20, -20, -20, -20, -20, -20, -20, -20},
        {-20, -25, -32, -50, -50, -32, -25, -20},
        {-15, -25, -20, -30, -30, -20, -25, -15},
        {-35, -35, -30, -30, -30, -30, -35, -35},
        {-30, -25, -10, 5, 5, -10, -25, -30},
        {-25, -20, -15, 0, 0, -15, -20, -25},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}};
    }
}
