package logic;

import engine.Board;
import engine.Move;
import gui.MainFrame;

import javax.swing.*;

public class Human extends Player{

    @Override
    void playMove() {

        if(this.game.getBoard().getAllMoves(this.isColor()).size()==0) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Računar je pobedio");
            //int cnt = this.game.getBoard().getAllMoves(this.isColor()).size();
            //this.game.makeMove(this, this.game.getBoard().getAllMoves(this.isColor()).get(random.nextInt(cnt)));
        }
    }
}
