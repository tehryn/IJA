package src.game;

import src.game.Board;
import src.game.Move;
import src.game.Move.Type;
import src.game.Card_stack;
import java.util.Vector;

/**
 * Class that holds history of moves.
 */
public class History {
    Vector<Move> stack = new Vector<Move>(0);
    public History() {}

    /**
     * Adds new move into history.
     * @param m Move that will be added.
     */
    public void push(Move m) {
        this.stack.add(m);
    }

    /**
     * Removes one move from history.
     * @return Removed move on succes, otherwise return move representing
     *         error.
     */
    public Move pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size()-1);
        }
        else {
            return Move.err();
        }
    }
}