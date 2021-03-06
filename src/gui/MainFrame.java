package gui;

import engine.Board;
import logic.Bot;
import logic.Game;
import logic.Human;
import logic.Player;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private static MainFrame instance = null;
    private BoardView boardView;
    private Game game;


    private MainFrame() {
    }

    private void initialise(){

        game = new Game();
        Player human = new Human();
        game.setPlayers(human, new Bot());

        boardView = new BoardView(game.getBoard());
        boardView.setPlayer(human);
        game.setBoardView(boardView);


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        this.setSize(screenWidth*2 / 3, screenHeight);
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

    public Game getGame() {
        return game;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
