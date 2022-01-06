package logic;

import engine.Board;
import engine.Move;

public class Human extends Player{

    @Override
    void playMove() {
        if(this.game.getBoard().getAllMoves(this.isColor()).size()>0) {
            int cnt = this.game.getBoard().getAllMoves(this.isColor()).size();
            this.game.makeMove(this, this.game.getBoard().getAllMoves(this.isColor()).get(random.nextInt(cnt)));
        }
    }
}
