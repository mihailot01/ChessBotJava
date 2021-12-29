package engine;

import engine.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    Piece[][] squares;

    public Board() {
        squares = new Piece[8][8];
        squares[0] = new Piece[]{new Rook(this, false), new Knight(this,false),
                                 new Bishop(this,false), new Queen(this,false),
                                 new King(this,false), new Bishop(this,false),
                                 new Knight(this,false), new Rook(this,false)
        };
        squares[1] = new Piece[]{new Pawn(this,false), new Pawn(this,false),
                                 new Pawn(this,false), new Pawn(this,false),
                                 new Pawn(this,false), new Pawn(this,false),
                                 new Pawn(this,false), new Pawn(this,false),
        };



        squares[6] = new Piece[]{new Pawn(this,true), new Pawn(this,true),
                new Pawn(this,true), new Pawn(this,true),
                new Pawn(this,true), new Pawn(this,true),
                new Pawn(this,true), new Pawn(this,true),
        };

        squares[7] = new Piece[]{new Rook(this,true), new Knight(this,true),
                                 new Bishop(this,true), new Queen(this,true),
                                 new King(this,true), new Bishop(this,true),
                                 new Knight(this,true), new Rook(this,true)
        };

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                if(squares[i][j]!=null)
                {
                    squares[i][j].setX(i);
                    squares[i][j].setY(j);
                }
    }

    public Board(Board b, Move m) {

    }


    public Piece getPiece(int x, int y){
        return squares[x][y];
    }

    public int getRating(boolean color){
        int sum = 0;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                sum += getPieceValue(squares[i][j], color);
        return sum;
    }

    private int getPieceValue(Piece p, boolean color) {
        if(p == null) return 0;
        if(p.getColor() == color) return p.getValue();
        return -p.getValue();
    }

    public List<Move> getAllMoves() {
        List<Move> listOfMoves = new ArrayList<>();
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(squares[i][j] != null)
                    listOfMoves.addAll(squares[i][j].getAvailableMoves());
        return listOfMoves;
    }

}
