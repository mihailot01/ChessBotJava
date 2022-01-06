package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    public int [][] diagonalDir = {{1,1},{1,-1}, {-1,1}, {-1,-1}};
    public int [][] ortogonalDir = {{1,0},{-1,0}, {0,1}, {0,-1}};
    public int [][] gDir = {{2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {1,-2}, {-1,2}, {-1,-2}};
    public int [][] nextDir = {{1,0},{1,1},{0,1},{0,-1},{-1,-1},{-1,0},{1,-1},{-1,1}};
    Board board;
    String name;
    boolean color;
    int x;
    int y;
    int value;
    boolean moved = false;

    public Piece(Board board, String name, int x, int y, boolean color) {
        this.board = board;
        this.color = color;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Piece(Board board, String name, boolean color, int value) {
        this.board = board;
        this.color = color;
        this.name = name;
        this.value = value;
    }

    public Piece(Piece p) {
        if(p == null) return; // proveri da li ce biti null
        this.board = p.board;  // OPASNO!
        this.name = p.name;
        this.color = p.color;
        this.x = p.x;
        this.y = p.y;
        this.value = p.value;
        this.moved = p.moved;
    }

    public List<Move> getMovesDir(Board b, int dx, int dy, boolean daleko) {
        List<Move> list = new ArrayList<>();
        int x = getX();
        int y = getY();
        while(b.can(this, x+dx, y+dy)) {
            x += dx;
            y += dy;
            list.add(new Move(this, x,y));
            if(b.getPiece(x,y) != null || !daleko) break;
        }
        return list;
    }

    public List<Move> filterMoves(List<Move> moves) {
        /*List<Move> list = new ArrayList<>();
        for(Move move: moves) {
            Board b = new Board(board, move);
            if(!b.isCheck(getColor())) list.add(move);
        }
        return list;*/
        return moves;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public List<Move> getAvailableMoves(Board b);

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
