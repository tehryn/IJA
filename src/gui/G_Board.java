package src.gui;

import src.game.Board;
import src.game.Card;
import src.gui.G_Card;
import src.gui.G_Working_stack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

@SuppressWarnings("serial")
public class G_Board extends JFrame implements ActionListener{

    private JMenuBar menu_bar = new JMenuBar();
    private Board playing_board = new Board();

    private JPanel panel_top = new JPanel(new FlowLayout());
    private JPanel panel_top_left = new JPanel(new FlowLayout());
    private JPanel panel_top_right = new JPanel(new FlowLayout());
    private JPanel panel_bottom = new JPanel(new FlowLayout());

    private ImageIcon[] images = new ImageIcon[9];
    private JLabel[] labels = new JLabel[9];

    private G_Working_stack zasobnik = new G_Working_stack();

    public G_Board(){
        super();
        init();
    }

    public void init_menu(){
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_S);

        JMenuItem menu_item = new JMenuItem("New", KeyEvent.VK_N);
        menu_item.setActionCommand("new");
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_item = new JMenuItem("Open", KeyEvent.VK_O);
        menu_item.setActionCommand("open");
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_item = new JMenuItem("Save", KeyEvent.VK_U);
        menu_item.setActionCommand("save");
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_item = new JMenuItem("Quit", KeyEvent.VK_K);
        menu_item.setActionCommand("quit");
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_bar.add(menu);
        setJMenuBar(menu_bar);
    }

    public void nacti_pokus_karty() {
        for (int i=0; i < 9; i++){
            String localpath = "./lib/cards/C" + (i+2) + ".png";

            File f = new File(localpath);
            if(!(f.exists() && !f.isDirectory())) {
                System.err.println("Cannot load a card (superif).");
                System.exit(0);
            }

            try {
                images[i] = new ImageIcon(localpath);
            } catch (Exception ex) {
                System.err.println("Cannot load a card.");
                System.exit(0);
            }

            labels[i] = new JLabel(images[i]);
            Dimension size = new Dimension(images[i].getIconWidth(), images[i].getIconHeight());
            labels[i].setPreferredSize(size);
        }
    }

    public void init() {
        init_menu();
        playing_board.new_game();

        panel_top.setBackground( new Color(0, 120, 0) );
        panel_bottom.setBackground( new Color(0, 120, 0) );
        panel_top_left.setBackground( new Color(0, 120, 0) );
        panel_top_right.setBackground( new Color(0, 120, 0) );

    //    nacti_pokus_karty();
    //    panel_top_left.add(labels[1]);
    //    panel_top_left.add(labels[2]);
    //    panel_top.add(panel_top_left, BorderLayout.WEST);

    ///    panel_top_right.add(labels[3]);
    //    panel_top_right.add(labels[4]);
    //    panel_top_right.add(labels[5]);
    //    panel_top_right.add(labels[6]);
    //    panel_top.add(panel_top_right, BorderLayout.EAST);

        add(panel_top, BorderLayout.NORTH);

    //    panel_bottom.add(labels[7]);
    //    panel_bottom.add(labels[8]);

        Card pokus_card = new Card(1, Card.Color.CLUBS);
        G_Card pokus_g_card = new G_Card(pokus_card);
        zasobnik.push(pokus_g_card);
        panel_bottom.add(zasobnik);

        add(panel_bottom);

        pack();

        setTitle("Solitaire");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(0, 0);
        setSize(600, 400);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("new"))
            playing_board.new_game();
        else if (s.equals("open"))
            playing_board.load_game("../examples/test.txt"); //TODO
        else if (s.equals("save"))
            playing_board.save_game("../examples/test2.txt"); //TODO
        else if (s.equals("quit"))
            System.exit(0);
    }

    public void run() {
        System.err.println("G_Board.run");
        EventQueue.invokeLater(() -> {
            this.setVisible(true);
        });
    }
}
