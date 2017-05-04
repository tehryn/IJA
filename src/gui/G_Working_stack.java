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
import src.game.Card;

@SuppressWarnings("serial")
public class G_Working_stack extends JLayeredPane {
    private Vector<G_Card> stack = new Vector<G_Card>();
    int x, y;
    int step;
    G_Working_stack(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
        y += 18*step;
        setPreferredSize(new Dimension(x, y));
        setBackground(new Color(0, 80, 0));
    }

    void my_repaint() {
        removeAll();
        Insets insets = getInsets();
        int top = 0;
        for (int i = 0; i < stack.size(); i++) {
            G_Card card = stack.get(i);
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
        G_Card gui_card = new G_Card(x, y, c);
        stack.add(index, gui_card);
    }
    
    void clear() {
        stack.clear();
    }
}