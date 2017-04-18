package src.gui;
import src.game.Board;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class gui extends JFrame{
    private JPanel mainPanel;
    private JPanel anotherPanel;
    private CardLayout layout;

    private ImageIcon c1;
    private ImageIcon c2;
    private ImageIcon c3;
    private ImageIcon c4;


    public gui(){
        init();
    }

    private void init() {
        mainPanel = new JPanel();
        anotherPanel = new JPanel();

        mainPanel.setBackground(new Color(0, 120, 0));
        anotherPanel.setBackground(new Color(0, 120, 0));


        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        layout = new CardLayout();
        mainPanel.setLayout(layout);

        ImageIcon c1 = new ImageIcon("./src/gui/c1.png");
        ImageIcon c2 = new ImageIcon("./src/gui/c2.png");
        ImageIcon c3 = new ImageIcon("./src/gui/c3.png");
        ImageIcon c4 = new ImageIcon("./src/gui/c4.png");

        JLabel label1 = new JLabel(c1);
        JLabel label2 = new JLabel(c2);
        JLabel label3 = new JLabel(c3);
        JLabel label4 = new JLabel(c4);

        mainPanel.add(label1);
        mainPanel.add(label2);
        add(mainPanel);
        anotherPanel.add(label3);
        anotherPanel.add(label4);
        add(anotherPanel,  BorderLayout.SOUTH);

        pack();

        setTitle("Solitare");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void run() {
        EventQueue.invokeLater(() -> {
            this.setVisible(true);
        });
    }
}