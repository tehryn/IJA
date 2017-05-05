package src.gui;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.border.MatteBorder;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Card_deck_hidden extends JPanel {
    Vector<G_Card> stack = new Vector<G_Card>();
    int x, y;
    Card clicked_card;
    G_Card_deck_hidden(int x, int y) {
        this.x = x;
        this.y = y;
        setLayout(new CardLayout());
        setPreferredSize(new Dimension(x, y));
        setBackground(new Color(0, 80, 0));
    }
    
    public void add_card(Card c, int index) {
        G_Card gui_card = new G_Card(x, y, c);
        stack.add(index, gui_card);
    }
    
    public void my_repaint() {
        removeAll();
        G_Card card = null;
        for (int i = stack.size() - 1; i >= 0; i--) {
            card = stack.get(i);
            add(card);
        }
        revalidate();
        repaint();
    }
    
    public void set_border() {
        if (!stack.isEmpty()) {
            stack.get(stack.size()-1).setBorder(new MatteBorder(2, 2, 2, 2, Color.red));
        }
    }
    
    public void unset_border() {
        if (!stack.isEmpty()) {
            stack.get(stack.size()-1).setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        }
    }
    
    public void clear() {
        stack.clear();
    }
}