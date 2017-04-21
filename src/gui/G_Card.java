package src.gui;
import src.game.Card;

import java.awt.*;
import javax.swing.*;
import java.io.File;

public class G_Card {
    private Card card;
    private ImageIcon image = null;
    private int position_y = 0;

    public G_Card (Card c) {
        super();
        card = c;
        if (card.is_error_card()) {
            System.err.println("Cannot create a card.");
            System.exit(0);
        }
        String localpath = ("./lib/cards/" + card.get_color()).toString() + card.get_value() + ".png";

        File f = new File(localpath);
        if(!(f.exists() && !f.isDirectory())) {
            System.err.println("Cannot load a card. It does not exist.");
            System.exit(0);
        }

        try {
            image = new ImageIcon(localpath);
        } catch (Exception ex) {
            System.err.println("Cannot load a card.");
            System.exit(0);
        }
    }

    public void set_y (int y) {
        System.err.println("card set_y");
        this.position_y = y;
    }

    public void draw (Graphics g, Component c) {
        System.err.println("card paint");
        this.image.paintIcon(c, g, 0, position_y);
    }
}
