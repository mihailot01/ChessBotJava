package engine;

import java.util.ArrayList;
import java.util.List;

public class Board {

    Piece[][] squares;

    public Board() {
        squares = new Piece[8][8];
        squares[0] = new Piece[]{new Piece(PieceType.ROOK, false), new Piece(PieceType.KNIGHT, false),
                                new Piece(PieceType.BISHOP, false), new Piece(PieceType.QUEEN, false),
                                new Piece(PieceType.KING, false), new Piece(PieceType.BISHOP, false),
                                new Piece(PieceType.KNIGHT, false), new Piece(PieceType.ROOK, false)
        };
        squares[1] = new Piece[]{new Piece(PieceType.PAWN,false), new Piece(PieceType.PAWN,false),
                                new Piece(PieceType.PAWN,false), new Piece(PieceType.PAWN,false),
                                new Piece(PieceType.PAWN,false), new Piece(PieceType.PAWN,false),
                                new Piece(PieceType.PAWN,false), new Piece(PieceType.PAWN,false)
        };


        squares[6] = new Piece[]{new Piece(PieceType.PAWN,true), new Piece(PieceType.PAWN,true),
                                 new Piece(PieceType.PAWN,true), new Piece(PieceType.PAWN,true),
                                 new Piece(PieceType.PAWN,true), new Piece(PieceType.PAWN,true),
                                 new Piece(PieceType.PAWN,true), new Piece(PieceType.PAWN,true)
        };
        squares[7] = new Piece[]{new Piece(PieceType.ROOK, true), new Piece(PieceType.KNIGHT, true),
                                 new Piece(PieceType.BISHOP, true), new Piece(PieceType.QUEEN, true),
                                 new Piece(PieceType.KING, true), new Piece(PieceType.BISHOP, true),
                                 new Piece(PieceType.KNIGHT, true), new Piece(PieceType.ROOK, true)
        };


    }


    public Piece getPiece(int x, int y){
        return squares[x][y];
    }


}
