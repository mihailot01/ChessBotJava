package gui;

import engine.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SquareView extends JPanel {
    private int x;
    private int y;
    private Piece piece;

    public SquareView(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;

        this.setPreferredSize(new Dimension(100,100));
        this.setMinimumSize(new Dimension(100,100));
        this.setMaximumSize(new Dimension(100,100));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if( (x+y) % 2 == 1)
            g.setColor(new Color(255,255,255));
        else
            g.setColor(new Color(0,0,0));

        g.fillRect(0,0,500,500);
        g.drawString(x+","+y,10,10);

        if(this.piece != null) {
            String path = "img/"+(this.piece.getColor()?"b":"w")+this.piece.getType()+".png";
            System.out.println(path);
            URL imgPath = getClass().getResource(path);
            Image img = new ImageIcon(imgPath).getImage();
            System.out.println(getWidth() + " " + getHeight());
            ((Graphics2D) g).drawImage(img, (int) (getWidth() * 0.1), (int) (getHeight() * 0.1), (int) (getWidth() * 0.8), (int) (getHeight() * 0.8), this);
        }

    }
}
