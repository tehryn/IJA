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

/**
 * Class representing working stack.
 * @author Matejka Jiri, xmatej52
 */
@SuppressWarnings("serial")
public class G_Working_stack extends JLayeredPane {
    /// @var stack of cards with no gui
    private Vector<Card> stack     = new Vector<Card>();
    
    /// @var stack of cards with gui
    private Vector<G_Card> g_stack = new Vector<G_Card>();

    /// @var Width of cards
    int x;
    
    // @var Heigth of cards
    int y;
    
    /// @var Shift of cards in stack.
    int step;
    
    /**
     * Constructor of class.
     * @param x    Width of cards.
     * @param y    Heigth of cards.
     * @param step Shift of cards in stack.
     */
    G_Working_stack(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
        setBackground(new Color(0, 80, 0));
    }

    /**
     * Repaints all cards in stack.
     */
    void my_repaint() {
        removeAll();
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

    /**
     * Adds card into stack.
     * @param c     Card that will be added.
     * @param index Index where will be added.
     */
    void add_card(Card c, int index) {
        G_Card card = new G_Card(x, y, c);
        stack.add(index, c);
        g_stack.add(index, card);
    }

    /**
     * Checks if stack contains any cards.
     * @return True if working stack has no cards, otherwise false.
     */
    public boolean is_empty() {
        return stack.isEmpty();
    }

    /**
     * Removes all cards from stack.
     */
    void clear() {
        stack.clear();
        g_stack.clear();
    }

    /**
     * Finds cards that lay on specific y coordinate,
     * @param y Coordinate where card lays
     * @return Card that lays on top of founded cards.
     */
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

    /**
     * Set border around specific card and all cards that lay under it.
     * @param card   First card where border will be added.
     * @param border Border that will be used.
     */
    void set_border(Card card, MatteBorder border) {
        boolean set = false;
        for (int i = 0; i < stack.size(); i++) {
            if (!set && card.equals(stack.get(i))) {
                set = true;
            }
            if (set) {
                g_stack.get(i).setBorder(border);
            }
        }
    }

    /**
     * Set border around card on top of stack.
     * @param border border that will be used.
     */
    void set_border(MatteBorder border) {
        if (!stack.isEmpty()) {
            g_stack.get(stack.size()-1).setBorder(border);
        }
	else {
	    setBorder(border);
	}
    }

    /**
     * Border of card on top of stack will be restarted to default value.
     */
     void unset_border() {
        if (!stack.isEmpty()) {
            g_stack.get(stack.size()-1).setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        }
	else {
	   setBorder(null);
	}
    }

    /**
     * Restart border to default value around specific card and all cards that lay under it.
     * @param card First card where border will be restarted.
     */
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