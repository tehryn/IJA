package src.gui;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Dimension;
import java.util.Vector;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Card_deck_hidden extends JPanel {
    Vector<G_Card> stack = new Vector<G_Card>();
    G_Card_deck_hidden(int x, int y) {
       super(new CardLayout());
       setPreferredSize(new Dimension(x, y));
       setBackground(new Color(0, 80, 0));
    }
    
    void add_card(Card c, int index) {
        G_Card gui_card = new G_Card(c);
        stack.add(index, gui_card);
    }
    
    void my_repaint() {
        removeAll();
        for (int i = 0; i < stack.size(); i++) {
            add(stack.get(i));
        }
        revalidate();
        repaint();
    }
    
    void clear() {
        stack.clear();
    }
}