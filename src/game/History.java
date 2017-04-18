package src.game;

import src.game.Board;
import src.game.Move;
import src.game.Move.Type;
import src.game.Card_stack;
import java.util.Vector;


public class History {
    Vector<Move> stack = new Vector<Move>(0);
    public History() {}

    public void push(Move m) {
        this.stack.add(m);
    }

    public Move pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size()-1);
        }
        else {
            return Move.err();
        }
    }
}