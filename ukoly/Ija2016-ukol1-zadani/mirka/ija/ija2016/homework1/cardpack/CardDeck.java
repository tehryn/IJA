package ija.ija2016.homework1.cardpack;
import ija.ija2016.homework1.cardpack.Card;

/** T��da reprezentuj�c� bal��ek karet. 
 * Karty lze vkl�dat a odeb�rat pouze z vrcholu bal��ku.
 */
public class CardDeck
{
    public int size; /** Aktu�ln� po�et karet v bal��ku. */
    protected Card[] StackOfCards;
    
    /** Vytvo�� a inicializuje bal��ek karet. 
     @param size Velikost bal��ku karet.
     */
    public CardDeck(int size){
        this.size = size;
    }
    
    /** Tov�rn� metoda, kter� vytvo�� bal�k 52 karet, 
     * 13 karet (hodnoty 1 a� 13) pro ka�dou barvu.
     * @return Instance t��dy CardDeck.
     */
    public static CardDeck createStandardDeck(){
        CardDeck standardDeck = new CardDeck(52);
        for(int i=0; i<52; i++)
        {
            standardDeck.StackOfCards
        }
    }
    
    /** Vlo�� kartu na vrchol bal��ku. 
     @param card Vkl�dan� karta.
     */
    public void put(Card card);
    
    /** Odebere kartu z vrcholu bal��ku. 
     * Pokud je bal��ek pr�zdn�, vrac� null. */
    public Card pop();
}