package src.gui;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.OverlayLayout;
import java.awt.Insets;
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
        setMinimumSize(new Dimension(x, y+500));
        setBackground(new Color(0, 80, 0));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(19, 0, 0, -140));
        ///this.setAlignmentY(JPanel.TOP_ALIGNMENT);
        //setLayout(new OverlayLayout(this));
        //setLayout(new FlowLayout());
        //setLayout(null);
    }

    void my_repaint() {
        removeAll();
        Insets insets = getInsets();
        for (int i = 0; i < stack.size(); i++) {
            G_Card card = stack.get(i);
            add(card, new Integer(i));
        }
        revalidate();
        repaint();
    }
    
    void add_card(Card c, int index) {
        G_Card gui_card = new G_Card(c);
        stack.add(index, gui_card);
    }
    
    void clear() {
        stack.clear();
    }
}