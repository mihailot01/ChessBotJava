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
        if(this.board.freeSquare(this.x+dir,this.y))
            moves.add(new Move(this, this.x+dir,this.y));
        if(!this.moved && this.board.freeSquare(this.x+2*dir,this.y))
            moves.add(new Move(this,this.x+2*dir,this.y));
//        if(this.board.enemyPiece(this,this.x+dir,this.y-1))
//            moves.add(new Move(this,this.x+dir,this.y-1));
//        if(this.board.enemyPiece(this,this.x+dir,this.y+1))
//            moves.add(new Move(this,this.x+dir,this.y+1));
        return moves;
    }
}
