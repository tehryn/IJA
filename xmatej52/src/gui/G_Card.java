/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   6. 5. 2017
 * content: GUI representation of card.
 */
package src.gui;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import src.game.Card;

/**
 * Class representing card.
 * @author Matejka Jiri (xmatej52)
 */
@SuppressWarnings("serial")
public class G_Card extends JLabel {
    /// @var Width of card
    int x;

    /// @var Height of card
    int y;

    /// @var Image of card.
    ImageIcon G_card;

    /**
     * Constructor of class.
     * @param x Width of card
     * @param y Height of card
     * @param c Card that will be represented by new object
     */
    G_Card(int x, int y, Card c) {
        this.x = x;
        this.y = y;
        G_card = null;
        if (c.is_visible()) {
            G_card = new ImageIcon("./lib/cards/" + c.get_color() + c.get_value() + ".png");
        }
        else {
            G_card = new ImageIcon("./lib/cards/back.png");
        }

        if (G_card == null) {
            System.out.println("Neni obrazek");
        }
        else {
            setIcon(G_card);
            this.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        }
    }

    /**
     * Paints card and resizes it.
     * @param g Graphocs that will be painted.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(G_card.getImage(),0,0,x,y,this);
    }
}