package logic;

import engine.Board;
import engine.Move;

import java.util.Deque;

public class Bot extends Player{

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

    public Bot() {}

    public Move getNextMove(Board b) {
        long start = System.currentTimeMillis();
        Deque<Move> moves = b.getAllMoves(this.isColor());

        Pair res = new Pair(null, 10000);
        int alfa = -10000;
        int beta = 10000;
        for(Move move: moves) {
            b.makeMove(move);
            AlfaBeta f = minimax(b, alfa, beta, !this.isColor(), 5);
            b.takeBackMove(move);
            if(f.p < res.val) {
                res.val = f.p;
                res.move = move;
            }
            alfa = f.alfa;
            beta = f.beta;
        }
        long end = System.currentTimeMillis();
        System.out.println("Vreme je "+(end-start)/1000.0);
        return res.move;
    }

    private AlfaBeta minimax(Board b, int alfa, int beta, boolean color, int depth) {
        int res = 10000;
        if(!color) res = -10000;
        Deque<Move> moves = b.getAllMoves(color);
        if(depth == 0)
            return new AlfaBeta(b.getRating(color), alfa, beta);
        for (Move move : moves) {
            //Board newBoard = new Board(b, move);
            b.makeMove(move);
            int p = minimax(b, alfa, beta, !color, depth - 1).p;
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
    }
}
