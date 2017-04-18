package src.game;

/**
 * Object representing Card. Card has value, color and visibility. <br>
 * Value is from 0 to 13, where 0 represents invalid card. <br>
 * Colors are ERR, CLUBS, DIAMONDS, HEARTS and SPADES, where ERR represents
 * invalid card. <br>
 * Visibility tells if card with its face up or not. Has values true and false. <br>
 * For checking validity of card, please use always color and looks for value
 * ERR.
 */
public class Card {
    /**
     * Enum representing color of cards.
     */
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

        /**
         * Checks if colors of 2 cards are similar.
         * @param   c Second card
         * @return    True if colors are similar, otherwise false.
         */
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

    /// Represents alue of card.
    protected int value          = 0;
    /// Represents color of card.
    protected Card.Color color   = Color.ERR;
    /// Represents visibility of card.
    protected boolean visibility = false;

    /**
     * Constructor of Card. Sets value to 0, color to ERR and visibility to false.
     */
    public Card(){}

    /**
     * Constructor of Card. Sets visibility to false.
     * @param  value Value of new card.
     * @param  color Color of new card.
     */
    public Card(int value, Card.Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Retrieve value of card.
     * @return Value of card.
     */
    public int get_value() { return this.value; }

    /**
     * Retrieve color of card.
     * @return color of card.
     */
    public Color get_color() { return this.color; }

    /**
     * Retrive visibility of card.
     * @return True if card is visible, otherwise false.
     */
    public boolean is_visible() { return this.visibility; }

    /**
     * Sets visibility of card to true.
     */
    public void make_visible() { this.visibility = true; }

    /**
     * Sets visibility of card to false.
     */
    public void make_hidden() { this.visibility = false; }

    /**
     * Checks if cards has similar colors.
     * @param  card Second card.
     * @return      True of cards colors are similar, otherwise false.
     */
    public boolean is_similar(Card card) {
        if (this.is_visible()) {
            return this.color.is_similar(card);
        }
        else {
            return false;
        }
    }

    /**
     * Checks if 2 cards are equal
     * @param  x Second card.
     * @return   True if cards are similar.
     */
    public boolean equals(Object x) {
        return (this.value == ((Card)x).value && this.color == ((Card)x).color);
    }

    /**
     * Arrange propre hassing of card.
     * @return Something...
     */
    public int hashCode() {
        return java.util.Objects.hash(this.value, this.color);
    }

    /**
     * Converts card to string.
     * @return String representing card.
     */
    public String toString() {
        if (this.visibility) {
            return String.valueOf(this.value) + "(" + this.color.toString() + ")" + "T";
        }
        else {
            return String.valueOf(this.value) + "(" + this.color.toString() + ")" + "F";
        }
    }
}