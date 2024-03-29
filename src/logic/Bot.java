package logic;

import engine.Board;
import engine.Move;
import gui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Bot extends Player{
    public static final int DUBINA = 3;


    public static class Pair {
        Move move;
        int val;
        public Pair(Move move, int val) {
            this.move = move;
            this.val = val;
        }
    }
    public static class AlfaBeta {
        int p;
        int alfa;
        int beta;
        public AlfaBeta(int p, int alfa, int beta) {
            this.p = p;
            this.alfa = alfa;
            this.beta = beta;
        }
    }

    Move [] listaStek = new Move[DUBINA+1];

    public Bot() {}

    public Move getNextMove(Board b) {
        long start = System.currentTimeMillis();
        Deque<Move> moves = b.getAllMoves(this.isColor());

        Pair res = new Pair(null, 10000000);
        int alfa = -10000;
        int beta = 10000;
        for(Move move: moves) {
//            System.out.println("e odigrao sam figuru " + move.getPiece().getName() + "sa poz " + move.getPiece().getX() + "," +
//                    move.getPiece().getY() + " na" + move.getEndX() + "," + move.getEndY());
            b.makeMove(move);
            AlfaBeta f = minimax(b, alfa, beta, !this.isColor(), DUBINA, move.isCaptures());
//            System.out.println(" MEDJU Ocena pozicije"+f.p);//b.getRating());
            b.takeBackMove(move);
            if(f.p < res.val) {
                res.val = f.p;
                res.move = move;
            }
            beta = Math.min(beta, f.beta);

        }
        long end = System.currentTimeMillis();
        System.out.println("Vreme je "+(end-start)/1000.0);
        System.out.println("Ocena pozicije"+res.val);//b.getRating());

        return res.move;
    }

    private AlfaBeta minimax(Board b, int alfa, int beta, boolean color, int depth, boolean jeo) {
        int res = 10000;
        if(!color) res = -10000;
        Deque<Move> moves = b.getAllMoves(color);
        if(moves.size()==0)
            return new AlfaBeta(color ? 9999:-9999,alfa,beta);
        if(depth == 0) {
            if(jeo) return minimaxJede(b,alfa,beta,color, 0, true);
            return new AlfaBeta(b.getRating(), alfa, beta);
        }
        for (Move move : moves) {
            b.makeMove(move);
            int p = minimax(b, alfa, beta, !color, depth - 1, move.isCaptures()).p;
            b.takeBackMove(move);
            if (!color) {
                if (p > res) res = p;
                if (res >= beta) return new AlfaBeta(res, alfa, beta); //odsecanje
                alfa = Math.max(alfa, res);
            } else {
                if (p < res) res = p;
                if (res <= alfa) return new AlfaBeta(res,alfa,beta); //odsecanje
                beta = Math.min(beta, res);
            }
        }
        return new AlfaBeta(res,alfa,beta);
    }

    private AlfaBeta minimaxJede(Board b, int alfa, int beta, boolean color, int duz, boolean jeo) {
        int res = b.getRating();
        //opt
        if (!color) {
            if (res >= beta) return new AlfaBeta(res, alfa, beta); //odsecanje
            alfa = Math.max(alfa, res);
        } else {
            if (res <= alfa) return new AlfaBeta(res,alfa,beta); //odsecanje
            beta = Math.min(beta, res);
        }
        Deque<Move> moves = b.getAllMoves(color);
        if(moves.size()==0)
            return new AlfaBeta(color ? 9999:+9999,alfa,beta);
        if(!moves.getFirst().isCaptures() || duz > 5 || Math.abs(b.getRating()) > 1100) {
//            System.out.println("OKKKKK");
            return new AlfaBeta(b.getRating(), alfa, beta);
        }
        int i = 0;
        List<Move> niz = new ArrayList<>(moves);
        Collections.sort(niz);
        for(Move move: niz) {
            b.makeMove(move);
            int p;
            if(jeo) p = minimaxJede(b, alfa, beta, !color, duz+1, move.isCaptures()).p;
            else p = b.getRating();
            b.takeBackMove(move);
            if (!color) {
                if (p > res) res = p;
                if (res >= beta) return new AlfaBeta(res, alfa, beta); //odsecanje
                alfa = Math.max(alfa, res);
            } else {
                if (p < res) res = p;
                if (res <= alfa) return new AlfaBeta(res,alfa,beta); //odsecanje
                beta = Math.min(beta, res);
            }
        }

        return new AlfaBeta(res,alfa,beta);
    }

    @Override
    void playMove() {
        Move move = this.getNextMove(this.game.getBoard());
        if (move != null) {
            this.game.makeMove(this, move);
            //this.game.changeTurn();
        }
        else
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Igrač je pobedio");
    }
}
