package engine.pieces;

import engine.Board;
import engine.Move;
import logic.Bot;

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
        int[][] potezi = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
        for(int i=0;i<8;i++)
            if(b.moze(this,this.x+potezi[i][0],this.y+potezi[i][1]))
                list.add(new Move(this,this.x+potezi[i][0],this.y+potezi[i][1]));
        /*for(int i = 0; i < gDir.length; i++)
            list.addAll(getMovesDir(b, gDir[i][0], gDir[i][1], false));*/
        return filterMoves(list);
    }
}
