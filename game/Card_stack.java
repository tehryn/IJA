package game;

import game.Card;
import java.util.Vector;
import java.lang.Math;

public class Card_stack {
    protected Vector<Card> stack = new Vector<Card>(0);
    public Card_stack(){}
    public int size() { return this.stack.size(); }
    public Card pop_random() {
        if (this.size() > 0) {
            int idx = (int) (Math.random() * this.stack.size());
            return this.stack.remove(idx);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }
    public Card pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size()-1);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }
    public Card pop_bottom() {
        if (this.stack.size() > 0) {
            return this.stack.remove(0);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }
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
    public void set_top_visible() {
        if (this.stack.size() > 0) {
            Card tmp = this.stack.get(this.stack.size() - 1);
            tmp.make_visible();
            this.stack.set(this.stack.size() - 1, tmp);
        }
    }
    public Card get(int idx) {
        if (idx < this.stack.size() && idx >= 0) {
            return this.stack.get(idx);
        }
        else {
            return new Card(0, Card.Color.ERR);
        }
    }
    public void insert_bottom(Card card) {
        this.stack.insertElementAt(card, 0);
    }
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

    public String toString() {
        String out = "";
        for(int i = 0; i < this.size(); i++) {
            out += this.stack.get(i);
        }
        return out;
    }
}