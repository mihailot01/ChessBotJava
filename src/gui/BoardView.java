package gui;

import engine.Board;
import engine.Move;
import engine.pieces.Piece;
import logic.Bot;
import logic.Game;
import logic.Player;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends JPanel {

    private Board board;
    private SquareView[][] squareViews = new SquareView[8][8];
    private Player player;
    private Piece selectedPiece;

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
        //test();
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        for(int i = 7; i >= 0; i--)
//            for (int j = 0; j < 8; j++) {
//                this.squareViews[i][j].paintComponent(g);
//            }
//    }

    private void myRepaint(){
        for(int i = 7; i >= 0; i--)
            for (int j = 0; j < 8; j++) {
                this.squareViews[i][j].setPiece(this.board.getPiece(i,j));

                (this.squareViews[i][j]).repaint();
            }
        //System.out.println(this.squareViews[2][2].getPiece().getClass());

    }

    private void test() {
        Bot ai = new Bot();
        Move m = ai.getNextMove(board);
        System.out.println(m.piece.getX() + " " + m.piece.getY() + m.piece.getName()+ "lolcina " +m.endX + " " + m.endY);
    }

    private void initializeSquareViews(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                squareViews[i][j]=new SquareView(i,j,board.getPiece(i,j),this);
    }

    private void resetClicked(){
        selectedPiece = null;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                squareViews[i][j].setClicked(0);
    }

    public void setClicked(SquareView squareView){
        if(squareView.getPiece()!=null && squareView.getPiece().getColor()==player.isColor()) {
            if(selectedPiece == squareView.getPiece())
            {
                resetClicked();
                return;
            }
            resetClicked();
            squareView.setClicked(1);
            selectedPiece = squareView.getPiece();
            List<Move> availableMoves = new ArrayList<>(squareView.getPiece().getAvailableMoves(board));
            for (Move m : availableMoves)
                squareViews[m.endX][m.endY].setClicked(2);
        }
        if(squareView.getClicked()==2){
            MainFrame.getInstance().getGame().makeMove(player,new Move(selectedPiece,squareView.getPosX(),squareView.getPosY()));
            //MainFrame.getInstance().getGame().changeTurn();
            resetClicked();
        }
    }


    public void setBoard(Board board) {
        System.out.println("SET BOARD");
        this.board = board;
        this.myRepaint();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
