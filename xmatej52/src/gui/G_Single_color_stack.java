/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   6. 5. 2017
 * content: GUI representation of single color stack.
 */
package src.gui;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.border.MatteBorder;
import src.game.Card;

/**
 * Class representing Single color stack.
 * @author Matejka Jiri (xmatej52)
 */
@SuppressWarnings("serial")
public class G_Single_color_stack extends JPanel {

    /// @var Stack of cards
    Vector<G_Card> stack = new Vector<G_Card>();

    /// @var Width of cards
    int x;

    // @var Height of cards
    int y;

    G_Single_color_stack(int x, int y) {
       this.x = x;
       this.y = y;
       setLayout(new CardLayout());
       setPreferredSize(new Dimension(x, y));
       setBackground(new Color(0, 80, 0));
    }

    /**
     * Adds gui representation of card into deck.
     * @param c     Card that will be added.
     * @param index Index where card will be added.
     */
    public void add_card(Card c, int index) {
        G_Card gui_card = new G_Card(x, y, c);
        stack.add(index, gui_card);
    }

    /**
     * Repaints all cards in deck.
     */
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

    /**
     * Set border around card on the top of stack. If deck is empty, border will
     * be around stack.
     * @param border Border that will be used.
     */
    public void set_border(MatteBorder border) {
        if (!stack.isEmpty()) {
            stack.get(stack.size()-1).setBorder(border);
        }
	else {
	    setBorder(border);
	}
    }

    /**
     * Restarts border to default value.
     */
    public void unset_border() {
        if (!stack.isEmpty()) {
            stack.get(stack.size()-1).setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        }
	else {
	    setBorder(null);
	}
    }

    /**
     * Removes all cards from deck.
     */
    public void clear() {
        stack.clear();
    }
}