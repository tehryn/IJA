package src.gui;

import src.game.Board;
import src.game.Card;
import src.gui.G_Card;
import src.gui.G_Working_stack;
import src.gui.G_Single_color_stack;
import src.gui.G_Card_deck_hidden;
import src.gui.G_Card_deck_visible;



import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;

@SuppressWarnings("serial")
public class G_Board extends JFrame {

/*    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("new"))
            game.new_game();
        else if (s.equals("open"))
            game.load_game("../examples/test.txt"); // TODO
        else if (s.equals("save"))
            game.save_game("../examples/test2.txt"); // TODO
        else if (s.equals("quit"))
            System.exit(0);
    }
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }*/
//    private JMenuBar menu_bar        = new JMenuBar(); // reprezentuje horni ovladaci listu
    private G_Working_stack[] working_stacks    = new G_Working_stack[7];
    private G_Single_color_stack[] color_stacks = new G_Single_color_stack[4];
    private G_Card_deck_visible visible_deck;
    private G_Card_deck_hidden  hidden_deck;

    private Board    game;

    public G_Board() {
//        init_menu();
        int x = 120;
        int y = 174;
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new G_Single_color_stack(x, y);
            add(color_stacks[i]);
        }
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new G_Working_stack(x, y);
            add(working_stacks[i]);
        }
        visible_deck = new G_Card_deck_visible(x, y);
        hidden_deck  = new G_Card_deck_hidden(x, y);
        add(visible_deck);
        add(hidden_deck);
        setSize(800, 600);
        setTitle("Solitare by xmatej52 and xmisov00");
        setBackground(new Color(0, 120, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    //    pack();
    }

/*
    public void init_menu(){
        JMenu menu = new JMenu("Game");
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
    }*/
}