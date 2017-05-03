package src.gui;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Card extends JLabel {
    G_Card(Card c) {
        ImageIcon card = null;
        if (c.is_visible()) {
            String str = "";
            switch(c.get_color()) {
                case HEARTS:   str += "H"; break;
                case SPADES:   str += "S"; break;
                case DIAMONDS: str += "D"; break;
                case CLUBS:    str += "C"; break;
                default:       str += "";
            }
            str += c.get_value();
            card = new ImageIcon("./lib/cards/" + str + ".png");
        }
        else {
            card = new ImageIcon("./lib/cards/back.png");
        }
        
        if (card == null) {
            //String dir = System.getProperty("user.dir");
            //System.out.println("current dir = " + dir);
            System.out.println("Neni obrazek");
        }
        else {
            setIcon(card);
        }
    }
}