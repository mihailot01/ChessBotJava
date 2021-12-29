package engine.pieces;

public class PieceCopyFactory {

    public static Piece vratiFiguru(Piece p) {
        if(p instanceof Bishop)
            return new Bishop((Bishop)p);
        if(p instanceof Knight)
            return new Knight((Knight) p);
        if(p instanceof King)
            return new King((King) p);
        if(p instanceof Pawn)
            return new Pawn((Pawn) p);
        if(p instanceof Queen)
            return new Queen((Queen) p);
        if(p instanceof Rook)
            return new Rook((Rook) p);
        return null;
    }
}
