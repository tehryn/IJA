package ija.ija2016.homework1.cardpack;

import ija.ija2016.homework1.cardpack.Card;

public class CardDeck {
    private int size;
    private Card[] deck;
    private int top;
    public CardDeck(int size) {
        this.size = size;
        this.deck = new Card[size];
        this.top  = -1;
    }

    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    public void put(Card card) {
        if (this.top+1 < this.size) {
            this.deck[++this.top] = card;
        }
        else {
            return;
        }
    }

    public Card pop() {
        if (this.top <= -1) {
            Card err = new Card(Card.Color.CLUBS, -1);
            return err;
        }
        else {
            return this.deck[this.top--];
        }
    }

    public static CardDeck createStandardDeck() {
        CardDeck deck = new CardDeck(52);
        for (int i = 1; i<=13; i++) {
            deck.put(new Card(Card.Color.CLUBS, i));
            deck.put(new Card(Card.Color.DIAMONDS, i));
            deck.put(new Card(Card.Color.HEARTS, i));
            deck.put(new Card(Card.Color.SPADES, i));
        }
        return deck;
    }

}