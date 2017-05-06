/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   22. 4. 2017
 * content: Implementation of card.
 */
package src.game;

/**
 * Object representing Card. Card has value, color and visibility. <br>
 * Value is from 0 to 13, where 0 represents invalid card. <br>
 * Colors are ERR, CLUBS, DIAMONDS, HEARTS and SPADES, where ERR represents
 * invalid card. <br>
 * Visibility tells if card with its face up or not. Has values true and false. <br>
 * For checking validity of card, please use always color and looks for value
 * ERR.
 * @author Matejka Jiri (xmatej52)
 */
public class Card {
    /**
     * Enum representing color of cards.
     */
    public static enum Color {
        ERR, CLUBS, DIAMONDS, HEARTS, SPADES;

        /**
         * Converts color into string.
         * @return String representing color.
         */
        public String toString() {
            switch(this) {
                case HEARTS:   return "H";
                case SPADES:   return "S";
                case DIAMONDS: return "D";
                case CLUBS:    return "C";
                default:       return "E";
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

    /// @var Represents alue of card.
    protected int value          = 0;
    /// @var Represents color of card.
    protected Card.Color color   = Color.ERR;
    /// @var Represents visibility of card.
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
     * Check if card has invalid color or value
     * @return True if card is invalid, otherwise false.
     */
    public boolean is_error_card() {
        return this.color == Color.ERR || this.value <= 0 || this.value > 13;
    }

    /**
     * Converts card to string.
     * @return String representing card.
     */
    public String toString() {
        String str = "";
        if (this.is_error_card()) {
            str += "--|EMPTY |";
        }
        else if(this.visibility) {
            switch(this.value) {
                case 13: str += "--| K"; break;
                case 12: str += "--| Q"; break;
                case 11: str += "--| J"; break;
                case 10: str += "--|10"; break;
                case  1: str += "--| A"; break;
                default: str += "--| " + this.value;
            }
            switch(this.color) {
                case HEARTS:   str += "(H) |"; break;
                case SPADES:   str += "(S) |"; break;
                case DIAMONDS: str += "(D) |"; break;
                case CLUBS:    str += "(C) |"; break;
            }
        }
        else {
            str += "--|OOOOOO|";
        }
        return str;
    }

    /**
     * Converts string into card
     * @param  str String representing card.
     * @return     Card that was represented by string. Error card when
     *             string was invalid.
     */
    public static Card string_to_card(String str) {
        int idx = 0;
        int size = str.length();
        Card card = new Card();
        if (idx < size && idx >= 0) {
            int value = 0;
            Color color = Color.ERR;
            switch(str.charAt(idx)) {
                case 'A': value = 1; break;
                case 'K': value = 13; break;
                case 'Q': value = 12; break;
                case 'J': value = 11; break;
                case '1': idx++; value = 10; break;
                default : value = str.charAt(idx) - '0';
            }
            idx+=2;
            if (idx < size && value > 0 && value < 14) {
                switch(str.charAt(idx)) {
                    case 'D': color = Color.DIAMONDS; break;
                    case 'H': color = Color.HEARTS; break;
                    case 'S': color = Color.SPADES; break;
                    case 'C': color = Color.CLUBS; break;
                    default : color = Color.ERR;
                }
                card = new Card(value, color);
                card.make_visible();
            }
            return card;
        }
        else {
            return card;
        }
    }

    /**
     * Converts card to string. Value of 11, 12 and 13 is represented by J, Q
     * and K. Value 1 as A. Information about visibility of card is not included
     * in string.
     * @param card Card that will be converted.
     * @return     String representing card.
     */
    public static String to_string(Card card) {
        String str = "";
        switch(card.value) {
            case  1: str += "A"; break;
            case 11: str += "J"; break;
            case 12: str += "Q"; break;
            case 13: str += "K"; break;
            default: str += card.value;
        }
        switch(card.color) {
            case HEARTS:   str += "(H)"; break;
            case SPADES:   str += "(S)"; break;
            case DIAMONDS: str += "(D)"; break;
            case CLUBS:    str += "(C)"; break;
            default:       str += "(ERR)";
        }
        return str;
    }

    /**
     * Converts card to string. Value of 11, 12 and 13 is represented by J, Q
     * and K. Value 10 as L Information about visibility of card is included
     * in string.
     * @return String representing card.
     */
    static String toString(Card card) {
        String str = "";
        switch(card.value) {
            case 10: str += "L"; break;
            case 11: str += "J"; break;
            case 12: str += "Q"; break;
            case 13: str += "K"; break;
            case  1: str += "A"; break;
            default: str += card.value;
        }
        switch(card.color) {
            case HEARTS:   str += "(H)"; break;
            case SPADES:   str += "(S)"; break;
            case DIAMONDS: str += "(D)"; break;
            case CLUBS:    str += "(C)"; break;
            default:       str += "(E)";
        }
        if (card.visibility) {
            str += 'T';
        }
        else {
            str += 'F';
        }
        return str;
    }
}