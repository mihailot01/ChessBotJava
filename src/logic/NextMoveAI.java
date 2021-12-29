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

        return minimax(b,-10000,10000, false, 2).move;
    }

    private Pair minimax(Board b, int alfa, int beta, boolean color, int depth) {
        Pair res = new Pair(null, 10000);
        if(color) res.val = -10000;
        List<Move> moves = b.getAllMoves();
        for(int i = 0; i < moves.size(); i++) {
            Board newBoard = new Board(b, moves.get(i));
            Pair p = minimax(newBoard, alfa, beta, !color, depth-1);
            if(color) {
                if(-p.val > res.val) res.val = -p.val;
                if(res.val >= beta) return res; //odsecanje
                alfa = Math.max(alfa, res.val);
            }
            else {
                if(-p.val < res.val) res.val = -p.val;
                if(res.val <= alfa) return res; //odsecanje
                beta = Math.min(beta, res.val);
            }
        }
        return res;
    }

}
