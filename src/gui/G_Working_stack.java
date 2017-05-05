package src.gui;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.OverlayLayout;
import java.awt.Insets;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.border.MatteBorder;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Working_stack extends JLayeredPane {
    private Vector<Card> stack     = new Vector<Card>();
    private Vector<G_Card> g_stack = new Vector<G_Card>();

    int x, y;
    int step;
    G_Working_stack(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
        setBackground(new Color(0, 80, 0));
    }

    void my_repaint() {
        removeAll();
        Insets insets = getInsets();
        int top = 0;
        for (int i = 0; i < g_stack.size(); i++) {
            G_Card card = g_stack.get(i);
            card.setBounds(0, top, x, y);
            top += step;
            add(card, new Integer(i));
        }
        if (top == 0) {
            setOpaque(true);
        }
        else {
            setOpaque(false);
        }
        revalidate();
        repaint();
    }
    
    void add_card(Card c, int index) {
        G_Card card = new G_Card(x, y, c);
        stack.add(index, c);
        g_stack.add(index, card);
    }
    
    public boolean is_empty() {
        return stack.isEmpty();
    }
    
    void clear() {
        stack.clear();
        g_stack.clear();
    }

    Card get_card(int y) {
        if (stack.isEmpty()) {
            return new Card(0, Card.Color.ERR);
        }
        else {
            for (int i = 0, j = step; i < stack.size(); i++, j+=step) {
                if (y < j) {
                    return stack.get(i);
                }
            }
            if (y < (this.y + (stack.size()-1)*step)) {
                return stack.get(stack.size()-1);
            }
            else {
                return new Card(0, Card.Color.ERR);
            }
        }
    }

    void set_border(Card card) {
        boolean set = false;
        for (int i = 0; i < stack.size(); i++) {
            if (!set && card.equals(stack.get(i))) {
                set = true;
            }
            if (set) {
                g_stack.get(i).setBorder(new MatteBorder(2, 2, 2, 2, Color.red));
            }
        }
    }

    void unset_border(Card card) {
        boolean unset = false;
        for (int i = 0; i < stack.size(); i++) {
            if (!unset && card.equals(stack.get(i))) {
                unset = true;
            }
            if (unset) {
                g_stack.get(i).setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            }
        }
    }
}