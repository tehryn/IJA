/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   22. 4. 2017
 * content: Implementation of stack that holds history of moves.
 */
package src.game;

import src.game.Board;
import src.game.Move;
import src.game.Move.Type;
import src.game.Card_stack;
import java.util.Vector;

/**
 * Class that holds history of moves.
 * @author Matejka Jiri (xmatej52)
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
     * @return Removed move on success, otherwise return move representing
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

    /**
     * Retrieve number of moves in history.
     * @return number of moves in history.
     */
    public int size() {
        return stack.size();
    }

    /**
     * Removes all moves from Vector.
     */
    public void clear() {
        stack.clear();
    }
}
