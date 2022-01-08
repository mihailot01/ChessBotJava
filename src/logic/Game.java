package logic;

import engine.Board;
import engine.Move;
import gui.BoardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    private Player player1;
    private Player player2;
    private Board board;
    private Player playerOnMove;
    private BoardView boardView;

    public Game() {
        this.board = new Board();
    }

    public void setPlayers(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;


        player1.setGame(this);
        player1.setOnTurn(false);
        player1.setColor(false);

        player2.setOnTurn(false);
        player2.setGame(this);
        player2.setColor(true);
    }

    public void startGame(){
        playerOnMove=player1;
        player1.setOnTurn(true);
    }

    public void makeMove(Player player, Move move) {
        System.out.println(move.toString());// +" "+player.getClass()+ " "+playerOnMove.getClass());
        if (player == playerOnMove) {
            //System.out.println("USO");
            this.board.makeMove(move);
            this.boardView.setBoard(board);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Timer timer = new Timer(100 ,evt -> changeTurn());
            timer.setRepeats(false);
            timer.start();

            //this.changeTurn();
        }
    }

    public void changeTurn() {
        if(this.playerOnMove == player1)
            this.setOnMove(player2);
        else
            this.setOnMove(player1);
    }

    private void setOnMove(Player player){
        this.playerOnMove.setOnTurn(false);
        this.playerOnMove = player;
        player.setOnTurn(true);

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }
}
