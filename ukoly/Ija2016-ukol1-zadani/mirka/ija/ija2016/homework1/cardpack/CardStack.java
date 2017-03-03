package ija.ija2016.homework1.cardpack;
import ija.ija2016.homework1.cardpack.Card;
import java.util.Objects;

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

	private int allocatedSize;
    
    protected Card[] StackOfCards;
    
    /** Vytvori a inicializuje zasobnik karet.
     * @param size Pocatecni velikost zasobniku karet.
     */
    public CardStack(int size){
		if(size < 0) return;
		this.size=0;
        this.allocatedSize = size;
		this.StackOfCards = new Card[this.allocatedSize];
    }
    
    /** Vlozi kartu na vrchol zasobniku.
     * @param card Vkladana karta.
     */
    public void put(Card card){
		if (this.isFull()) return;
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

	public boolean isFull(){
        return (this.size >= this.allocatedSize);
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
            if (card.equals(this.StackOfCards[i]))
                break;
        }
        if (i>=this.size)
            return null;
        
        CardStack newStack = new CardStack(this.size-i);
		int newSize=this.size;
        
		for (; i<this.size; i++)
        {
            newStack.put(this.StackOfCards[i]);
            this.StackOfCards[i]=null;
			newSize--;
        }
		this.size=newSize;
        return newStack;
    }

	public boolean equals(Object o)
    {
		if (this == o) return true;
		if (!(o instanceof CardStack)) {
			return false;
		}

		CardStack cardStack = (CardStack) o;

		if(this.size != cardStack.size) return false;

		for(int i=0; i<this.size; i++)
		{
			if(this.StackOfCards[i].equals(cardStack.StackOfCards[i]))
				continue;
			else
				return false;
		}
		return true;
    }

	@Override
    public int hashCode() {
        return Objects.hash(allocatedSize);
    }
}
