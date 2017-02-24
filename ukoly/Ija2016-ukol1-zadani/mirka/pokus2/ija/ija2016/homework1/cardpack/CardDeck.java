package ija.ija2016.homework1.cardpack;
import ija.ija2016.homework1.cardpack.Card;

/** Tøída reprezentující balíèek karet. 
 * Karty lze vkládat a odebírat pouze z vrcholu balíèku.
 */
public class CardDeck
{
    public int size; /** Aktuální poèet karet v balíèku. */
    protected Card[] StackOfCards;
    
    /** Vytvoøí a inicializuje balíèek karet. 
     @param size Velikost balíèku karet.
     */
    public CardDeck(int size){
        this.size = size;
    }
    
    /** Tovární metoda, která vytvoøí balík 52 karet, 
     * 13 karet (hodnoty 1 a 13) pro kadou barvu.
     * @return Instance tøídy CardDeck.
     */
    public static CardDeck createStandardDeck(){
        CardDeck standardDeck = new CardDeck(52);
        for(int i=0; i<52; i++)
        {
            standardDeck.StackOfCards
        }
    }
    
    /** Vloí kartu na vrchol balíèku. 
     @param card Vkládaná karta.
     */
    public void put(Card card);
    
    /** Odebere kartu z vrcholu balíèku. 
     * Pokud je balíèek prázdnı, vrací null. */
    public Card pop();
}