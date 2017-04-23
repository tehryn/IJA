package src.game;

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
        WW, CC, WC, CW, VW, VC, H, ERR;
    }
    protected int from;
    protected int to;
    protected int card_count;
    protected Type type;

    /**
     * Creates new move.
     * @param  type       Type of move.
     * @param  from       From which stack/deck card(s) was taken.
     * @param  to         To which stack/deck card(s) was added.
     * @param  card_count Number of cards that was moved.
     */
    public Move(Type type, int from, int to, int card_count) {
        this.type       = type;
        this.to         = to;
        this.from       = from;
        this.card_count = card_count;
    }

    /**
     * Creates invalid move.
     * @return Invalid move.
     */
    public static Move err() {
        return new Move(Move.Type.ERR, 0,0,0);
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
     * Retrieve count of cards that were moved.
     * @return Number of cards that were moved.
     */
    public int get_count() {
        return this.card_count;
    }
}