package ija.ija2016.homework1.cardpack;

/** Tøída reprezentující jednu kartu. 
 * Karta obsahuje informaci o své hodnotì (1 až 13) a barvì. 
 * Tyto informace jsou nastaveny konstruktorem. 
 * Hodnota 1 reprezentuje eso (ace), 11 až 13 postupnì kluk (jack), 
 * královna (queen) a král (king). Barvu definuje výètový typ Color. 
 * Textová reprezentace karty má podobu "hodnota(barva)", 
 * napø. srdcový král "K(H)" */
public class Card
{
    public static enum Color{
        CLUBS,      // Clubs (køíže).
        DIAMONDS,   // Diamonds (káry).
        HEARTS,     // Hearts (srdce).
        SPADES;      //Spides (piky).
    };
    public int value; /** Barva karty. */
    Card.Color color; /** Hodnota karty. */
    
    /** Konstruktor nastavující barvu a hodnotu karty. */
    public Card(Card.Color c, int value){ 
        this.value = value;
        this.color = c;
    }
}

