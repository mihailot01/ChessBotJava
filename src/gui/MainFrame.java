package gui;

import engine.Board;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private static MainFrame instance = null;
    private static BoardView boardView;

    private MainFrame() {
    }

    private void initialise(){

        boardView = new BoardView(new Board());

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        this.setSize(screenWidth / 2, screenHeight / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AI Chess Bot");

        this.add(boardView);
    }


    public static MainFrame getInstance(){
        if (instance == null){
            instance  = new MainFrame();
            instance.initialise();

        }
        return instance;
    }
}
