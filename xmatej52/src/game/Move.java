/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   22. 4. 2017
 * content: Implementation of game moves.
 */
package src.game;
import src.game.Card;
/**
 * Class representing moves. There are 8 possible moves:
 * WW  - Move between two working stacks.<br>
 * CC  - Move between two color stacks.<br>
 * WC  - Move between working stack(from) and color stack(to).<br>
 * CW  - Move between color stack(from) amd working stack(to).<br>
 * VW  - Move between visible deck and working stack.<br>
 * VC  - Move between visible deck and color stack.<br>
 * H   - Move representing click on hidden deck. <br>
 * ERR - Invalid move.
 * @author Matejka Jiri (xmatej52)
 */
public class Move {
    /**
     * Enum that holds movement identifiers.
     */
    public static enum Type {
        WW, CC, WC, CW, VW, VC, H, INV;
    }
    protected int from;
    protected int to;
    protected Card card;
    protected Type type;
    protected boolean card_turned;

    /**
     * Creates new move.
     * @param  type        Type of move.
     * @param  from        From which stack/deck card(s) was taken.
     * @param  to          To which stack/deck card(s) was added.
     * @param  card_count  Number of cards that was moved.
     * @param  card_turned Tells if card was turned on top of stack after move.
     */
    Move(Type type, int from, int to, Card card, boolean card_turned) {
        this.type        = type;
        this.to          = to;
        this.from        = from;
        this.card        = card;
        this.card_turned = card_turned;
    }

    /**
     * Creates invalid move.
     * @return Invalid move.
     */
    public static Move err() {
        return new Move(Type.INV, 0,0, new Card(0, Card.Color.ERR), false);
    }

    /**
     * Retrieve type of movement.
     * @return Type of movement.
     */
    public Type get_type() {
        return this.type;
    }

    /**
     * Retrieve ID of stack where cards was added.
     * @return ID of stack.
     */
    public int get_to() {
        return this.to;
    }

    /**
     * Retrieve ID of stack from where cards was taken.
     * @return ID of stack
     */
    public int get_from() {
        return this.from;
    }

    /**
     * Retrieve card that was moved.
     * @return Number of cards that were moved.
     */
    public Card get_card() {
        return this.card;
    }

    /**
     * Tells if card was turned after this move.
     * @return True if card was turned, otherwise false.
     */
    boolean was_turned() {
        return card_turned;
    }

    /**
     * Checks if move is invalid (type of move is INV)
     * @return true if move is invalid.
     */
    public boolean is_move_invalid() {
        return type == Type.INV;
    }

    public String toString() {
        switch(type) {
            case WW:
                return "working " + from + " working " + to + ' ' + Card.to_string(card) + "\n";
            case WC:
                return "working " + from + " color " + to + "\n";
            case CW:
                return "color " + from + " working " + to + "\n";
            case CC:
                return "color " + from + " color " + to + "\n";
            case VW:
                return "visible working " + to + "\n";
            case VC:
                return "visible color " + to + "\n";
            case  H:
                return "hidden";
            default: return "";
        }
    }

}