package engine.pieces;

import engine.Board;
import engine.Move;

import java.util.List;

public abstract class Piece {

    Board board;
    String name;
    boolean color;
    int x;
    int y;
    int value;

    public Piece(Board board, String name, int x, int y, boolean color) {
        this.board = board;
        this.color = color;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Piece(Board board, String name, boolean color) {
        this.board = board;
        this.color = color;
        this.name = name;
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

    abstract public List<Move> getAvailableMoves();

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

}
