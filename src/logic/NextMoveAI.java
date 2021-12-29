package logic;

import engine.Board;
import engine.Move;

import java.util.List;

public class NextMoveAI {

    public class Pair {
        Move move;
        int val;
        public Pair(Move move, int val) {
            this.move = move;
            this.val = val;
        }
    }

    public NextMoveAI() {}

    public Move getNextMove(Board b) {

        List<Move> moves = b.getAllMoves(false);
        Pair res = new Pair(null, 10000);
        for(Move move: moves) {
            Board newBoard = new Board(b, move);
            int p = minimax(newBoard, -10000, 10000, true, 0);
            if(p < res.val) {
                res.val = p;
                res.move = move;
            }
        }
        return res.move;
    }

    private int minimax(Board b, int alfa, int beta, boolean color, int depth) {
        int res = 10000;
        if(color) res = -10000;
        List<Move> moves = b.getAllMoves(color);
        if(depth == 0)
            return b.getRating(color);
        for(int i = 0; i < moves.size(); i++) {
            Board newBoard = new Board(b, moves.get(i));
            int p = minimax(newBoard, alfa, beta, !color, depth-1);
            if(color) {
                if(p > res) res = -p;
                if(res >= beta) return res; //odsecanje
                alfa = Math.max(alfa, res);
            }
            else {
                if(p < res) res = -p;
                if(res <= alfa) return res; //odsecanje
                beta = Math.min(beta, res);
            }
        }
        return res;
    }
}
