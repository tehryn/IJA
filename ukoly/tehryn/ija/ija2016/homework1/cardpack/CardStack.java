package ija.ija2016.homework1.cardpack;

public class CardStack {
    private int size;
    private Card[] stack;
    private int top;

    public CardStack(int size) {
        this.top   = -1;
        this.size  = size;
        this.stack = new Card[size];
    }

    public boolean equals(Object x) {
        if (((CardStack)x).top != this.top) {
            return false;
        }
        else {
            for (int i = 0; i <= this.top; i++) {
                if (((CardStack)x).stack[i].equals(this.stack[i]) == false) {
                    return false;
                }
            }
            return true;
        }
    }
    public int hashCode() {
        return java.util.Objects.hash(this.size);
    }
    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void put(Card card) {
        if (this.top+1 < this.size) {
            this.stack[++this.top] = card;
        }
        else {
            return;
        }
    }

    public void put(CardStack stack) {
        for (int i = 0; i <= stack.top; i++) {
            this.put(stack.stack[i]);
        }
    }

    public int size() {
        return this.top + 1;
    }

    public CardStack takeFrom(Card card) {
        int new_size = 0;
        for (int i = this.top; i > -1; i--) {
            new_size++;
            if (card.equals(this.stack[i])) {
                break;
            }

        }
        if (new_size > 0) {
            CardStack new_stack = new CardStack(new_size);
            for (int i = this.top - new_size + 1; i <= this.top; i++) {
                new_stack.put(this.stack[i]);
            }
            this.top -= new_size;
            return new_stack;
        }
        else {
            return null;
        }
    }
}