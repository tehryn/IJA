package src.gui;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Working_stack extends JPanel {
    private Vector<G_Card> stack = new Vector<G_Card>();
    private int top = 0;
    G_Working_stack(int x, int y) {
        setMinimumSize(new Dimension(x, y));
        setBackground(new Color(0, 80, 0));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new FlowLayout());
    }

    void my_repaint() {
        removeAll();
        top = 0;
        for (int i = 0; i < stack.size(); i++) {
            add(stack.get(i));
        }
        revalidate();
        repaint();
    }
    
    void add_card(Card c, int index) {
        G_Card gui_card = new G_Card(c);
        gui_card.setBorder(BorderFactory.createEmptyBorder( top, 0, 0, -120 ));
        top += 30;
        stack.add(index, gui_card);
    }
    
    void clear() {
        stack.clear();
    }
}