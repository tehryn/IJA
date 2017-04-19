package src.game;

import src.game.Card;
import java.util.Vector;
import java.lang.Math;

/**
 * Base class of Card_deck_visible, Card_deck_hidden, Working_stack and
 * Single_color_stack.
 */
public class Card_stack {
    protected Vector<Card> stack = new Vector<Card>(0);

    /**
     * Constructor of class.
     */
    public Card_stack(){}

    /**
     * Retrieve size of card stack. (Number of cards in stack)
     * @return Size of card stack.
     */
    public int size() { return this.stack.size(); }

    /**
     * Pops random card from stack.
     * @return Random card.
     */
    public Card pop_random() {
        if (this.size() > 0) {
            int idx = (int) (Math.random() * this.stack.size());
            return this.stack.remove(idx);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }

    /**
     * Pops card on top of stack.
     * @return Popped card.
     */
    public Card pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size()-1);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }

    /**
     * Pops card from top of stack.
     * @return Popped card
     */
    public Card pop_bottom() {
        if (this.stack.size() > 0) {
            return this.stack.remove(0);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }

    /**
     * Pushes card on top of stack, regardless of the cards in stack.
     * @param card Card that will be pushed.
     */
    public void force_push(Card card) {
        this.stack.add(card);
    }
    public Card top() {
        if (this.stack.size() > 0) {
            return this.stack.get(this.stack.size()-1);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }
    public Card bottom() {
        if (this.stack.size() > 0) {
            return this.stack.get(0);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }

    /**
     * Makes card on top of the stack visible.
     */
    public void set_top_visible() {
        if (this.stack.size() > 0) {
            Card tmp = this.stack.get(this.stack.size() - 1);
            tmp.make_visible();
            this.stack.set(this.stack.size() - 1, tmp);
        }
    }

    /**
     * Retrieve card from specific index,
     * @param  idx Index of card.
     * @return     In case on succes, card is returned, otherwise card with color
     *             of ERR is returned.
     */
    public Card get(int idx) {
        if (idx < this.stack.size() && idx >= 0) {
            return this.stack.get(idx);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }

    /**
     * Inserts card on bottom of stack, regardless of the cards in stack.
     * @param card Card that will be inserted.
     */
    public void insert_bottom(Card card) {
        this.stack.insertElementAt(card, 0);
    }

    /**
     * Creates new stack of 52 cards.
     * @return New stack of cards.
     */
    public static Card_stack new_deck() {
        Card_stack deck = new Card_stack();
        for (int i = 1; i <= 13; i++) {
            deck.force_push(new Card(i, Card.Color.DIAMONDS));
            deck.force_push(new Card(i, Card.Color.SPADES));
            deck.force_push(new Card(i, Card.Color.HEARTS));
            deck.force_push(new Card(i, Card.Color.CLUBS));
        }
        return deck;
    }

    /**
     * Converts Stack into string.
     * @return String representing stack.
     */
    public String toString() {
        String out = "";
        for(int i = 0; i < this.size(); i++) {
            out += this.stack.get(i);
        }
        return out;
    }
}