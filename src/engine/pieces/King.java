package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{


    public King(Board board, boolean color) {
        super(board,"KING", color,0);
    }

    public King(Board board, int x, int y, boolean color) {
        super(board,"KING", x, y,  color);
        setValue(0);
    }

    public King(King k) {
        super(k);
    }

    private boolean canCastle(Board b, Piece rook){
        if(this.moved || rook == null || !(rook instanceof Rook) || rook.moved)
            return false;
        int rookY = rook.getY();
        if(rookY<this.y){
            for(int i=rookY+1;i<this.y;i++)
                if(!b.freeSquare(this.x,i) || (i!=rookY+1 && b.isAttackedSquare(this.x,i,color)))
                    return false;
            return true;
        }
        else {
            for(int i=rookY-1;i>this.y;i--)
                if(!b.freeSquare(this.x,i) || b.isAttackedSquare(this.x,i,color))
                    return false;
            return true;
        }
    }

    @Override
    public List<Move> getAvailableMoves(Board b) {
        List<Move> list = new ArrayList<>();
        for (int[] ints : nextDir) list.addAll(getMovesDir(b, ints[0], ints[1], false));
        if(!this.isMoved()) {
            Piece kingsRook = b.getPiece(this.x, this.y + 3);
            if (canCastle(b, kingsRook)) {
                Move m = new Move(this, this.x, this.y + 2);
                m.setCastleMove2(new Move(kingsRook, kingsRook.x, kingsRook.y-2));
                list.add(m);
            }
            Piece queensRook = b.getPiece(this.x,this.y - 4);
            if(canCastle(b,queensRook)){
                Move m = new Move(this, this.x, this.y -2);
                m.setCastleMove2(new Move(queensRook, queensRook.x, queensRook.y+3));
                list.add(m);
            }
        }
        return filterMoves(list);

    }
}
