package ija.ija2016.homework1.cardpack;
import ija.ija2016.homework1.cardpack.Card;

/** Trida reprezentujici balicek karet. 
 * Karty lze vkladat a odebirat pouze z vrcholu balicku.
 */
public class CardDeck
{
    public int size; /** Aktualni pocet karet v balicku. */
    protected Card[] StackOfCards;
    
    /** Vytvori a inicializuje balicek karet. 
     @param size Velikost balicku karet.
     */
    public CardDeck(int size){
        this.size = size;
    }
    
    /** Tovarni metoda, ktera vytvori balik 52 karet, 
     * 13 karet (hodnoty 1 az 13) pro kazdou barvu.
     * @return Instance tridy CardDeck.
     */
    public static CardDeck createStandardDeck(){
        CardDeck standardDeck = new CardDeck(52);
        for(int i=0; i<13; i++)
        {
            standardDeck.StackOfCards[4*i+0] = new Card(Card.Color.CLUBS, i+1);
            standardDeck.StackOfCards[4*i+1] = new Card(Card.Color.DIAMONDS, i+1);
            standardDeck.StackOfCards[4*i+2] = new Card(Card.Color.HEARTS, i+1);
            standardDeck.StackOfCards[4*i+3] = new Card(Card.Color.SPADES, i+1);
        }
        return standardDeck;
    }
    
    /** Vlozi kartu na vrchol balicku. 
     @param card Vkladana karta.
     */
    public void put(Card card){
        this.StackOfCards[this.size] = card;
        this.size++;
    }
    
    /** Odebere kartu z vrcholu balicku. 
     * Pokud je balicek prazdny, vraci null. */
    public Card pop(){
        if (this.size <=0) return null;
        Card card = this.StackOfCards[this.size-1];
        this.StackOfCards[this.size-1] = null;
        this.size--;
        return card;
    }
}