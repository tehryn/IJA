package src.gui;
import src.game.Card;

import java.awt.*;
import javax.swing.*;
import java.util.Vector;

// Trida reprezentujici zasobnik karet. Ve hre lezi ve spodnim radku.
@SuppressWarnings("serial")
public class G_Working_stack extends JComponent {
    protected Vector<G_Card> stack = new Vector<G_Card>(0); // zasobnik karet
    private int position_y = 0; // o kolik ma byt posunuta nove vlozena karta

    public G_Working_stack () {}

    public void push (G_Card card) { // obycejny push
        System.err.println("push, y=" + position_y); // pomocny vypis
        this.stack.add(card); // push
        card.set_y(position_y); // posunuti dane karty
        position_y += 10; // pro nasledujici kartu
        System.err.println("y=" + position_y); // pomocny vypis
    }

    // postupne odebirani karet od indexu az po vrchol zasobniku
    // musi byt jeste vyzkouseno
    public G_Card remove_indexed (G_Card card, int index) {
        if (this.stack.size() < 0)
            return null;
        if (index >= this.stack.size())
            return null;

        // mazani jedne karty
        position_y = 10 * (this.stack.size() - 1);
        return this.stack.remove(index);
    }

    // zda se karta nachazi v zasobniku
    public boolean is_in_stack (G_Card card) {
        return this.stack.contains(card);
    }

    // zda se karta nachazi v zasobniku, vraci index
    public int find_in_stack (G_Card card) {
        return this.stack.indexOf(card);
    }

    /* Mozna se bude pozdeji hodit.
    E 	elementAt(int index)
    Returns the component at the specified index.
     */

    @Override
    public void paint (Graphics g) { // snaha o vykresleni zasobniku
        System.err.println("printComponent "); // pomocny vypis
        super.paintComponent(g);

        for (G_Card card : stack) { // prochazim vsechny karty v zasobniku (snad od 0. indexu)
            card.draw(g, this);
        }

    }
}
