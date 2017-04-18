package src.game;

public class Move {
    public static enum Type {
        WW, CC, WC, CW, VW, VC, H, ERR;
    }
    protected int from;
    protected int to;
    protected int card_count;
    protected Type type;
    public Move(Type type, int from, int to, int card_count) {
        this.type       = type;
        this.to         = to;
        this.from       = from;
        this.card_count = card_count;
    }
    public static Move err() {
        return new Move(Move.Type.ERR, 0,0,0);
    }
    public Type get_type() {
        return this.type;
    }
    public int get_to() {
        return this.to;
    }
    public int get_from() {
        return this.from;
    }
    public int get_count() {
        return this.card_count;
    }
}