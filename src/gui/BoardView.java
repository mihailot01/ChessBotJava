package gui;

import engine.Board;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    private Board board;
    private SquareView[][] squareViews = new SquareView[8][8];

    public BoardView(Board board) {
        this.board = board;
        this.initializeSquareViews();

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        JPanel[] rows = new JPanel[8];
        this.setLayout(boxLayout);

        this.setPreferredSize(new Dimension(200,200));
        this.setMaximumSize(new Dimension(200,200));
        this.setMinimumSize(new Dimension(200,200));
        for(int i = 7; i >= 0; i--) {
            rows[i] = new JPanel();
            rows[i].setLayout(new BoxLayout(rows[i],BoxLayout.X_AXIS));
            for (int j = 0; j < 8; j++)
                rows[i].add(squareViews[i][j]);
            this.add(rows[i]);
        }
    }

    private void initializeSquareViews(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                squareViews[i][j]=new SquareView(i,j,board.getPiece(i,j));
    }


}
