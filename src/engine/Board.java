package engine;

import engine.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {

    Piece[][] squares;
    List<Piece> pieces;

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

    public Board(Board b, Move move) {
        squares = new Piece[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                squares[i][j] = PieceCopyFactory.vratiFiguru(b.squares[i][j]);
                if(squares[i][j] != null)
                    squares[i][j].setBoard(this);
            }
        makeMove(move);
    }

    public void makeMove(Move move) {
        squares[move.piece.getX()][move.piece.getY()] = null;
        squares[move.endX][move.endY] = move.piece;
        //move.piece.setX(move.endX);
        //move.piece.setY(move.endY);
        //obrisi iz liste figura ako treba
    }

    public Piece getPiece(int x, int y){
        return squares[x][y];
    }

    public int getRating(boolean color){
        int sum = 0;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                sum += getPieceValue(squares[i][j]);
        return sum;
    }

    private int getPieceValue(Piece p) {
        if(p == null) return 0;
        if(p.getColor()) return p.getValue();
        return -p.getValue();
    }

    public List<Move> getAllMoves(boolean color) {
        List<Move> listOfMoves = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null && squares[i][j].getColor() == color)
                    listOfMoves.addAll(squares[i][j].getAvailableMoves(this));
            }
        }
        return listOfMoves;
    }

    public boolean moze(Piece p, int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8 && (getPiece(x,y) == null || getPiece(x,y).getColor() != p.getColor());
    }

    public boolean isCheck(boolean color) { //da li je kralj ove boje ugozen
        /*List<Move> list = getAllMoves(!color);
        for(Move move: list) {
            Piece endP = squares[move.endX][move.endY];
            if(endP != null && Objects.equals(endP.getName(), "KING") && endP.getColor() == color)
                return true;
        }*/
        return false;
    }

}
