package gui;

import engine.Move;
import engine.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class SquareView extends JPanel {
    private int x;
    private int y;
    private Piece piece;
    private BoardView boardView;
    private int clicked;
    private Move move;

    public SquareView(int x, int y, Piece piece, BoardView boardView) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.boardView = boardView;
        this.clicked = 0;

        this.setPreferredSize(new Dimension(100,100));
        this.setMinimumSize(new Dimension(100,100));
        this.setMaximumSize(new Dimension(100,100));

        SquareView squareView = this;

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boardView.setClicked(squareView);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        if(x==2 && y==2)
//            System.out.println("PAINT SQUARE VIEW");

        if( (x+y) % 2 == 1)
            g.setColor(new Color(255,255,255));
        else
            g.setColor(new Color(0,0,0));

        g.fillRect(0,0,500,500);

        if(clicked==1)
            g.setColor(new Color(0,0,230,170));
        if(clicked==2)
            g.setColor(new Color(0,0,200,70));

        g.fillRect(0,0,500,500);
        g.drawString(x+","+y,10,10);

        if(this.piece != null) {
            String path = "img/"+(this.piece.getColor()?"b":"w")+this.piece.getName()+".png";
            //System.out.println(path);

            URL imgPath = getClass().getResource(path);
            Image img = new ImageIcon(imgPath).getImage();
            //System.out.println(getWidth() + " " + getHeight());
            ((Graphics2D) g).drawImage(img, (int) (getWidth() * 0.1), (int) (getHeight() * 0.1), (int) (getWidth() * 0.8), (int) (getHeight() * 0.8), this);
        }

    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
        this.repaint();
    }

    public int getClicked() {
        return clicked;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.revalidate();
        this.repaint();
    }

    public int getPosX() {
        return x;
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public int getPosY() {
        return y;
    }

    public void setPosY(int y) {
        this.y = y;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
