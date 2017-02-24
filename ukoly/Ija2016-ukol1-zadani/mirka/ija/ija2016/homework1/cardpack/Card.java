package ija.ija2016.homework1.cardpack;

/** T��da reprezentuj�c� jednu kartu. 
 * Karta obsahuje informaci o sv� hodnot� (1 a� 13) a barv�. 
 * Tyto informace jsou nastaveny konstruktorem. 
 * Hodnota 1 reprezentuje eso (ace), 11 a� 13 postupn� kluk (jack), 
 * kr�lovna (queen) a kr�l (king). Barvu definuje v��tov� typ Color. 
 * Textov� reprezentace karty m� podobu "hodnota(barva)", 
 * nap�. srdcov� kr�l "K(H)" */
public class Card
{
    public static enum Color{
        CLUBS,      // Clubs (k��e).
        DIAMONDS,   // Diamonds (k�ry).
        HEARTS,     // Hearts (srdce).
        SPADES;      //Spides (piky).
    };
    public int value; /** Barva karty. */
    Card.Color color; /** Hodnota karty. */
    
    /** Konstruktor nastavuj�c� barvu a hodnotu karty. */
    public Card(Card.Color c, int value){ 
        this.value = value;
        this.color = c;
    }
}

