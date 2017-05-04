package src.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import src.game.Card;

@SuppressWarnings("serial")
public class G_Card extends JLabel {
    int x, y;
    ImageIcon G_card;
    Card card;
    G_Card(int x, int y, Card c) {
        this.x = x;
        this.y = y;
        card = c;
        G_card = null;
        if (c.is_visible()) {
            G_card = new ImageIcon("./lib/cards/" + c.get_color() + c.get_value() + ".png");
        }
        else {
            G_card = new ImageIcon("./lib/cards/back.png");
        }
        
        if (G_card == null) {
            //String dir = System.getProperty("user.dir");
            //System.out.println("current dir = " + dir);
            System.out.println("Neni obrazek");
        }
        else {
            setIcon(G_card);
            this.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            /*addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(card);
            }
        });*/
        }
    }
    
    public Card get_card() {
        return card;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(G_card.getImage(),0,0,x,y,this);
    }
}