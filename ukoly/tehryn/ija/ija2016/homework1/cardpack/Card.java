package ija.ija2016.homework1.cardpack;

public class Card {
    public static enum Color {
        CLUBS, DIAMONDS, HEARTS, SPADES;

        public String toString() {
            switch(this) {
                case CLUBS    : return "C";
                case DIAMONDS : return "D";
                case HEARTS   : return "H";
                case SPADES   : return "S";
                default       : return "";
            }
        }
    };
    private int value;
    private Card.Color color;

    public Card(Card.Color c, int value) {
        this.color = c;
        this.value = value;
    }

    public Card.Color color() {
        return this.color;
    }

    public int value() {
        return this.value;
    }

    public boolean equals(Object x) {
        return (this.value() == ((Card)x).value() && this.color() == ((Card)x).color());
    }
    public int hashCode() {
        return java.util.Objects.hash(this.value, this.color);
    }
    public String toString() {
        switch(this.value) {
            case 1  : return "A"  + "(" + this.color.toString() + ")";
            case 10 : return "10" + "(" + this.color.toString() + ")";
            case 11 : return "J"  + "(" + this.color.toString() + ")";
            case 12 : return "Q"  + "(" + this.color.toString() + ")";
            case 13 : return "K"  + "(" + this.color.toString() + ")";
            default : return String.valueOf(this.value) + "(" + this.color.toString() + ")";
        }
    }
}