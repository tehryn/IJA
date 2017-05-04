package src.gui;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Card_deck_visible extends JPanel {
    Vector<G_Card> stack = new Vector<G_Card>();
    int x, y;
    Card clicked_card;
    G_Card_deck_visible(int x, int y) {
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
        for (int i = 0; i < stack.size(); i++) {
            card = stack.get(i);
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked(e);
                }
            });
            add(card);
        }
        revalidate();
        repaint();
    }

    private void clicked(MouseEvent e) {
        for (int i = 0; i < stack.size(); i++) {
            if (e.getSource() == stack.get(i)) {
                System.out.println(stack.get(i).get_card());
                clicked_card = stack.get(i).get_card();
            }
        }
    }
    
    public void clear() {
        stack.clear();
    }
}