package logic;

import engine.Board;
import engine.Move;

public class Human extends Player{

    @Override
    void playMove() {
        if(this.game.getBoard().getAllMoves(this.isColor()).size()>0)
            this.game.makeMove(this,this.game.getBoard().getAllMoves(this.isColor()).get(0));
    }
}
