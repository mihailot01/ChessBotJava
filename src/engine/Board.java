package engine;

import engine.pieces.*;

import java.util.*;

public class Board {

    private Piece[][] squares;
    private List<Piece> pieces;
    private Stack<Move> moves;
    private int boardValue = 0;
    private Piece whiteKing;
    private Piece blackKing;

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

        moves = new Stack<>();

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                if(squares[i][j]!=null)
                {
                    squares[i][j].setAwardPositions();
                    squares[i][j].setX(i);
                    squares[i][j].setY(j);
                    boardValue += getAwardPosition(squares[i][j]);
                }

        whiteKing = squares[0][4];
        blackKing = squares[7][4];
    }

    public Board(Board b, Move move) {
        squares = new Piece[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                squares[i][j] = PieceCopyFactory.vratiFiguru(b.squares[i][j]);
                if(squares[i][j] != null)
                    squares[i][j].setBoard(this);
            }
        moves = new Stack<>();
        moves.addAll(b.moves);
        makeMove(move);
    }

    public void makeMove(Move move) {

        //System.out.println(move.toString());

        Piece p = move.getPiece();//squares[move.piece.getX()][move.piece.getY()];

        move.setCapturedPiece(squares[move.getEndX()][move.getEndY()]);

        boardValue -= getAwardPosition(p);
        boardValue -= getAwardPosition(move.getCapturedPiece());

        //System.out.println(p);
        move.setStartX(p.getX());
        move.setStartY(p.getY());

        squares[move.getPiece().getX()][move.getPiece().getY()] = null;
        squares[move.getEndX()][move.getEndY()] = p;

        p.setX(move.getEndX());
        p.setY(move.getEndY());

        moves.add(move);

        move.setOldMoved(p.isMoved());

        p.setMoved(true);

        if(move.getCastleMove2()!=null)
            this.makeMove(move.getCastleMove2());

        if(move.isPromotion()) {
            boardValue-=getPieceValue(move.getPiece());
            move.setPiece(new Queen(move.getPiece().getBoard(), move.getEndX(), move.getEndY(), move.getPiece().getColor()));
            boardValue+=getPieceValue(move.getPiece());
        }
        boardValue -= getPieceValue(move.getCapturedPiece());
        boardValue += getAwardPosition(move.getPiece());
//        move.piece.setX(move.endX);
//        move.piece.setY(move.endY);
        //obrisi iz liste figura ako treba
    }

    public void takeBackMove(Move move){
        squares[move.getStartX()][move.getStartY()] = move.getPiece();
        squares[move.getEndX()][move.getEndY()] = move.getCapturedPiece();

        boardValue -= getAwardPosition(move.getPiece());

        move.getPiece().setMoved(move.isOldMoved());
        move.getPiece().setX(move.getStartX());
        move.getPiece().setY(move.getStartY());
        if(move.getCapturedPiece() != null) {
            move.getCapturedPiece().setX(move.getEndX());
            move.getCapturedPiece().setY(move.getEndY());
        }
        moves.pop();

        if(move.getCastleMove2()!=null)
            this.takeBackMove(move.getCastleMove2());

        if(move.isPromotion()){
            boardValue-=getPieceValue(move.getPiece());
            move.setPiece(new Pawn(move.getPiece().getBoard(), move.getStartX(), move.getStartY(), move.getPiece().getColor()));
            boardValue+=getPieceValue(move.getPiece());
        }

        boardValue += getPieceValue(move.getCapturedPiece());
        boardValue += getAwardPosition(move.getCapturedPiece());
        boardValue += getAwardPosition(move.getPiece());
    }

    public Piece getPiece(int x, int y){
        return squares[x][y];
    }

    public int getRating(){
        return boardValue;
    }

    public int getAwardPosition(Piece p) {
        if(p == null) return 0;
        return p.getAwardPosition();
    }

    private int getPieceValue(Piece p) {
        if(p == null) return 0;
        if(p.getColor()) return -p.getValue();  //crni ima negativan value
        return p.getValue();
    }

    public Deque<Move> getAllMoves(boolean color) {
        Deque<Move> listOfMoves = new ArrayDeque<>();
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null && squares[i][j].getColor() == color) {
//                    if(color)
//                        System.out.println("Dodajemo " + squares[i][j].getX() + ","+squares[i][j].getY() + " " + squares[i][j].getName());
                    List<Move> tmp = squares[i][j].getAvailableMoves(this);
                    for (Move m : tmp){
                        if(m.isCaptures())
                            listOfMoves.addFirst(m);
                        else
                            listOfMoves.addLast(m);
                    }
                }
            }
        }
        //Collections.sort(listOfMoves);
        return listOfMoves;
    }

    public boolean insideTable(int x, int y){
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    public boolean can(Piece p, int x, int y) {
        return insideTable(x,y) && (getPiece(x,y) == null || getPiece(x,y).getColor() != p.getColor());
    }

    public boolean freeSquare(int x, int y){
        return insideTable(x,y) && (getPiece(x,y) == null);
    }

    public boolean enemyPiece(Piece p, int x, int y){
        return insideTable(x,y) && getPiece(x,y)!= null && getPiece(x,y).getColor() != p.getColor();
    }

    public boolean enemyPiece(boolean c, int x, int y){
        return insideTable(x,y) && getPiece(x,y)!= null && getPiece(x,y).getColor() != c;
    }

    public boolean isCheck(boolean color) {
        Piece king = whiteKing;
        if(color) king = blackKing;
        return isAttackedSquare(king.getX(), king.getY(), king.getColor());
    }

    public boolean isAttackedSquare(int x, int y, boolean color){
        int pawnDir = color ? 1 : -1;

        //KING ATTACKS
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++)
                if(insideTable(x+i,y+j) && getPiece(x+i,y+j)!=null && getPiece(x+i, y+j) instanceof King && getPiece(x+i,y+j).getColor()!=color)
                    return true;

        //PAWN
        int xp = x - pawnDir;
        if(enemyPiece(color,xp,y+1) && getPiece(xp,y+1) instanceof Pawn)
            return true;
        if(enemyPiece(color,xp,y-1) && getPiece(xp,y-1) instanceof Pawn)
            return true;

        //KNIGHT
        int[][]  dir= {{2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {1,-2}, {-1,2}, {-1,-2}};
        for(int i=0;i<8;i++)
            if(enemyPiece(color,x+dir[i][0],y+dir[i][1]) && (getPiece(x+dir[i][0],y+dir[i][1]) instanceof Knight)) {
                //System.out.println("SAH KONJEM");
                return true;
            }
        //ROOK
        for(int j=-1;j<=1;j+=2)
            for(int i=1;i<8;i++){
                if(!insideTable(x,y+i*j))
                    break;

                Piece p = getPiece(x,y+i*j);

                if(p != null){
                    if((p instanceof Rook || p instanceof Queen) && (p.getColor()!=color))
                        return true;
                    break;
                }
            }

        for(int j=-1;j<=1;j+=2)
            for(int i=1;i<8;i++){
                if(!insideTable(x+i*j,y))
                    break;

                Piece p = getPiece(x+i*j,y);

                if(p != null){
                    if((p instanceof Rook || p instanceof Queen) && (p.getColor()!=color))
                        return true;
                    break;
                }
            }

        //BISHOP
        for(int j=-1;j<=1;j+=2) {
            for (int i = 1; i < 8; i++) {
                if (!insideTable(x + i * j , y+ i * j))
                    break;

                Piece p = getPiece(x + i * j, y + i * j);

                if (p != null) {
                    if ((p instanceof Bishop || p instanceof Queen) && (p.getColor() != color))
                        return true;
                    break;
                }
            }
            for (int i = 1; i < 8; i++) {

                if (!insideTable(x + i * j, y - i * j))
                    break;

                Piece p = getPiece(x + i * j, y - i * j);

                if (p != null) {
                    if ((p instanceof Bishop || p instanceof Queen) && (p.getColor() != color))
                        return true;
                    break;
                }
            }
        }
        return false;
    }


}
