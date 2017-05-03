package src.gui;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class G_Card_deck_hidden extends JPanel {
    G_Card_deck_hidden(int x, int y) {
        setBackground(new Color(0, 80, 0));
        setPreferedSize(new Dimension(x, y));
    }
}