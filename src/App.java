import gui.MainFrame;

import javax.swing.*;

public class App{

    public static void main(String[] args) {

        MainFrame b = MainFrame.getInstance();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.getGame().startGame();
    }


}
