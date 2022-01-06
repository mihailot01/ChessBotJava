package logic;

import engine.Board;
import engine.Move;

import java.util.Deque;
import java.util.List;

public class Bot extends Player{

    public static class Pair {
        Move move;
        int val;
        public Pair(Move move, int val) {
            this.move = move;
            this.val = val;
        }
    }

    public Bot() {}

    public Move getNextMove(Board b) {
        long start = System.currentTimeMillis();
        Deque<Move> moves = b.getAllMoves(this.isColor());

        Pair res = new Pair(null, 10000);
        for(Move move: moves) {
//            System.out.println(move.piece.getName() + "(" + move.piece.getX() + "," + move.piece.getY() + ")" +"->("+ move.endX +","+ move.endY+")");
            //Board newBoard = new Board(b, move);
           b.makeMove(move);
            int p = minimax(b, -10000, 10000, !this.isColor(), 5);
            //System.out.println(p);
            b.takeBackMove(move);
            if(p < res.val) {
                res.val = p;
                res.move = move;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);
        return res.move;
    }

    private int minimax(Board b, int alfa, int beta, boolean color, int depth) {
        int res = 10000;
        if(!color) res = -10000;
        Deque<Move> moves = b.getAllMoves(color);
        if(depth == 0)
            return b.getRating(color);
        for (Move move : moves) {
            //Board newBoard = new Board(b, move);
            b.makeMove(move);
            int p = minimax(b, alfa, beta, !color, depth - 1);
            b.takeBackMove(move);
            if (!color) {
                if (p > res) res = p;
                if (res >= beta) return res; //odsecanje
                alfa = Math.max(alfa, res);
            } else {
                if (p < res) res = p;
                if (res <= alfa) return res; //odsecanje
                beta = Math.min(beta, res);
            }
        }
        return res;
    }

    @Override
    void playMove() {
        Move move = this.getNextMove(this.game.getBoard());
//        try {
//                Thread.sleep(6000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        if (move != null) {
            this.game.makeMove(this, move);
            //this.game.changeTurn();
        }
    }
}
