package src.gui;
import src.game.Card;

import java.awt.*;
import javax.swing.*;
import java.util.Vector;

@SuppressWarnings("serial")
public class G_Working_stack extends JComponent {
    protected Vector<G_Card> stack = new Vector<G_Card>(0);
    private int position_y = 0;

    public G_Working_stack () {}

    public void push (G_Card card) {
        System.err.println("push" + position_y);
        this.stack.add(card);
        card.set_y(position_y);
        position_y += 10;
        System.err.println(position_y);
    }

    public G_Card remove_indexed (G_Card card, int index) {
        position_y = 10 * this.stack.size();
        if (this.stack.size() < 0)
            return null;
        if (index >= this.stack.size())
            return null;
        return this.stack.remove(index);
    }

    public boolean is_in_stack (G_Card card) {
        return this.stack.contains(card);
    }

    public int find_in_stack (G_Card card) {
        return this.stack.indexOf(card);
    }

    /* Mozna se bude hodit.
    E 	elementAt(int index)
    Returns the component at the specified index.
     */

    @Override
    public void paint (Graphics g) {
        super.paintComponent(g);
        System.err.println("printComponent ");

        for (G_Card card : stack) {
            card.draw(g, this);
        }
    }
}
