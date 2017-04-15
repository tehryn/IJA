package game;

public class Card {
    public static enum Color {
        ERR, CLUBS, DIAMONDS, HEARTS, SPADES;

        public String toString() {
            switch(this) {
                case CLUBS    : return "C";
                case DIAMONDS : return "D";
                case HEARTS   : return "H";
                case SPADES   : return "S";
                default       : return "ERR";
            }
        }

        public boolean is_similar(Card c) {
            Color color = c.color;
            if ((color == DIAMONDS || color == HEARTS) && (this == HEARTS || this == DIAMONDS)) {
                return true;
            }
            else if ((color == SPADES || color == CLUBS) && (this == SPADES || this == CLUBS)) {
                return true;
            }
            else {
                return false;
            }
        }
    };
    protected int value          = 0;
    protected Card.Color color   = Color.ERR;
    protected boolean visibility = false;
    public Card(){}
    public Card(int value, Card.Color color) {
        this.value = value;
        this.color = color;
    }
    public int get_value() { return this.value; }
    public Color get_color() { return this.color; }
    public boolean is_visible() { return this.visibility; }
    public void make_visible() { this.visibility = true; }
    public void make_hidden() { this.visibility = false; }
    public boolean is_similar(Card card) {
        if (this.is_visible()) {
            return this.color.is_similar(card);
        }
        else {
            return false;
        }
    }
    public boolean equals(Object x) {
        return (this.value == ((Card)x).value && this.color == ((Card)x).color);
    }
    public int hashCode() {
        return java.util.Objects.hash(this.value, this.color);
    }
    public String toString() {
        if (this.visibility) {
            return String.valueOf(this.value) + "(" + this.color.toString() + ")" + "T";
        }
        else {
            return String.valueOf(this.value) + "(" + this.color.toString() + ")" + "F";
        }
    }
}