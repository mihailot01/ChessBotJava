package logic;

import engine.Board;
import engine.Move;

import java.util.Random;

public abstract class Player {

    public Random random = new Random();
    protected Game game;
    private boolean onTurn;
    private boolean color;

    abstract void playMove();

    public boolean isOnTurn() {
        return onTurn;
    }

    public void setOnTurn(boolean onTurn) {
        this.onTurn = onTurn;
        if(this.onTurn)
            this.playMove();
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
