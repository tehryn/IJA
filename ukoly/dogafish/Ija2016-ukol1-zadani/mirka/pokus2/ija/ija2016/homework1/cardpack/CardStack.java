package ija.ija2016.homework1.cardpack;
import ija.ija2016.homework1.cardpack.Card;

/** Trida reprezentujici zasobnik karet. 
 * Karty v zasobniku nelze prohazovat. 
 * Karty lze vkladat a vybirat z vrcholu hromadne.
 */
public class CardStack{
    
    private int size; /** Aktualni pocet karet v zasobniku. */

    /**
     * Aktualni pocet karet v zasobniku.
     * @return Vraci pocet karet.
     */
    public int size(){
        return this.size;
    }
    
    protected Card[] StackOfCards;
    
    /** Vytvori a inicializuje zasobnik karet.
     * @param size Pocatecni velikost zasobniku karet.
     */
    public CardStack(int size){
        this.size = size;
    }
    
    /** Vlozi kartu na vrchol zasobniku.
     * @param card Vkladana karta.
     */
    public void put(Card card){
        this.StackOfCards[this.size] = card;
        this.size++;
    }
    
    /** Vlozi karty ze zasobniku stack na vrchol zasobniku.
     * Karty vklada ve stejnem poradi, 
     * v jakem jsou uvedeny v zasobniku stack. 
     * Karta na vrcholu zasobniku vkladanych karet stack bude
     * i na vrcholu tohoto zasobniku.
     * @param stack Zasobnik vkladanych karet.
     */
    public void put(CardStack stack){
        for (int i=0; i<stack.size; i++)
        {
            this.put(stack.StackOfCards[i]);
            stack.StackOfCards[i]=null;
        }
        stack.size=0;
    }
    
    /** Test, zda je zasobnik prazdny.
     * @return Vraci true, pokud je zasobnik prazdny.
     */
    public boolean isEmpty(){
        return (!(this.size == 0));
    }
    
    /** Metoda odebere ze zasobniku sekvenci karet
     * od zadané karty az po vrchol zasobniku. 
     * Pokud je hledana karta na vrcholu, 
     * bude v sekvenci pouze jedna karta.
     * @param card Hledaná karta.
     * @return Zasobnik karet obsahujici odebranou sekvenci. 
     *      Pokud hledana karta v zasobniku neni, vraci null.
     */
    public CardStack takeFrom(Card card){
        int i=0;
        
        for (; i<this.size; i++)
        {
            if (card.equalsCards(card, this.StackOfCards[i]))
                break;
        }
        if (i>=this.size)
            return null;
        
        CardStack newStack = new CardStack(0);
        for (; i<this.size; i++)
        {
            newStack.put(this.StackOfCards[i]);
            this.StackOfCards[i]=null;
        }
        return newStack;
    }
}