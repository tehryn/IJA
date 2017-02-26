package ija.ija2016.homework1.cardpack;

/** Trida reprezentujici jednu kartu. 
 * Karta obsahuje informaci o sve hodnote (1 az 13) a barve. 
 * Tyto informace jsou nastaveny konstruktorem. 
 * Hodnota 1 reprezentuje eso (ace), 11 az 13 postupne kluk (jack), 
 * kralovna (queen) a kral (king). Barvu definuje vicovy typ Color. 
 * Textova reprezentace karty ma podobu "hodnota(barva)", 
 * napr. srdcovy kral "K(H)" */
public class Card
{
    public static enum Color{
        CLUBS,      // Clubs (krize).
        DIAMONDS,   // Diamonds (kary).
        HEARTS,     // Hearts (srdce).
        SPADES;      //Spides (piky).
    };
    private int value; /** Hodnota karty. */

    /**
     * Hodnota karty.
     * @return Vraci hodnotu karty.
     */
    public int value(){
        return this.value;
    }
    
    private Card.Color color; /** Barva karty. */

    /**
     * Barva karty.
     * @return Vraci barvu karty.
     */
    public Card.Color color(){
        return this.color;
    }
    
    /** Konstruktor nastavujici barvu a hodnotu karty. */
    public Card(Card.Color c, int value){ 
        this.value = value;
        this.color = c;
    }
    
    /** Test, zda jsou si 2 karty stejne.
     *
     * @param card1 Prvni karta.
     * @param card2 Druha karta.
     * @return Vraci true, pokud jsou 2 karty rovny. Jinak vraci false.
     */
    public boolean equalsCards(Card card1, Card card2)
    {
        return ((card2.color == card1.color) && (card2.value == card1.value));
    }
    
    private String cardValueToString()
    {
        if (this.value == 1)
            return "A";
        
        if (this.value<10)
            return String.valueOf(this.value);
        
        switch(this.value){
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return "ERROR";
        }
    }
    private String cardColorToString()
    {
        switch(this.color){
            case CLUBS:
                return "C";
            case DIAMONDS:
                return "D";
            case HEARTS:
                return "H";
            case SPADES:
                return "S";
            default:
                return "ERROR";
        }
    }
    
    public String toString()
    {
        return (cardValueToString() + "(" + cardColorToString() + ")");
    }
}

