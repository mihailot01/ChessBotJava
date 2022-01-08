package logic;

import engine.Board;
import engine.Move;

import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("e odigrao sam figuru " + move.piece.getName() + "sa poz " + move.piece.getX() + "," +
                    move.piece.getY() + " na" + move.endX + "," + move.endY);
            b.makeMove(move);
            AlfaBeta f = minimax(b, alfa, beta, !this.isColor(), 0);
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
            return minimaxJede(b,alfa,beta,color, 0);
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

    private AlfaBeta minimaxJede(Board b, int alfa, int beta, boolean color, int duz) {
        int res = 10000;
        if(!color) res = -10000;
        Deque<Move> moves = b.getAllMoves(color);
        if(moves.isEmpty() || !moves.getFirst().captures || duz > 10) {
//            System.out.println("OKKKKK");
            return new AlfaBeta(b.getRating(color), alfa, beta);
        }
        int i = 0;
        List<Move> niz = new ArrayList<>();
        for(Move move: moves) {
            i++;
            System.out.println("na dubini " + duz + " figura " + move.piece.getName() + "na poz " + move.piece.getX() + "," + move.piece.getY() + " je otisla na" +
                    move.endX + "," + move.endY);
            if(!move.captures){
//                System.out.println(i);
                break;
            }
            niz.add(move);
        }
        Collections.sort(niz);
        for(Move move: niz) {
            b.makeMove(move);
            int p = minimaxJede(b, alfa, beta, !color, duz+1).p;
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
