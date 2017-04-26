package src.gui;
import src.game.Card;

import java.awt.*;
import javax.swing.*;
import java.io.File;

// Trida reprezentujici kartu. Obsahuje obrazek a samotnou kartu, ktera obsahuje veskere informace (hodnota a barva karty).
// Dale obsahuje posunuji vuci ose Y.
public class G_Card {
    private Card card; // Karta - hodnota, barva, odkrytost
    private ImageIcon image = null; // Obrazek karty - jeste se musi dodelat zadni strana (TODO)
    private int position_y = 0; // Posunuti po ose Y (O kolik se ma karta posunout dolu.)

    public G_Card (Card c) {
        super();
        card = c;
        if (card.is_error_card()) { // pri chybe muze byt vracena chybova karta
            System.err.println("Cannot create a card.");
            System.exit(0);
        }
        String localpath = ("./lib/cards/" + card.get_color()).toString() + card.get_value() + ".png";

        File f = new File(localpath); // kontrola, zda dany soubor existuje
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

    // nastaveni posunuti na ose Y
    public void set_y (int y) {
        System.err.println("card set_y"); // jen pomocny vypis
        this.position_y = y;
    }

    // moje funkce na vykresleni, nic by nemela pretezovat
    public void draw (Graphics g, Component c) {
        System.err.println("card paint"); // jen pomocny vypis
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("Pokus.", 100, 10);
        this.image.paintIcon(c, g, 0, position_y); // snaha o vykresleni obrazku do tridy typu JComponent s posunutim o position_y
    }
}
