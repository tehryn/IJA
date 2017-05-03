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
import java.awt.Dimension;
import java.awt.FlowLayout;

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

    private Board game = new Board();

    public G_Board() {
//        init_menu();
        getContentPane().setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(0,120,0));
        int x = 120;
        int y = 174;
        visible_deck = new G_Card_deck_visible(x, y);
        hidden_deck  = new G_Card_deck_hidden(x, y);
        add(hidden_deck);
        add(visible_deck);
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new G_Single_color_stack(x, y);
            add(color_stacks[i]);
        }
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new G_Working_stack(x, y);
            add(working_stacks[i]);
        }
        setMinimumSize(new Dimension(850, 400));
        setTitle("Solitare by xmatej52 and xmisov00");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    //    pack();
        game.new_game();
    //    System.out.println(game);
        repain_all();
    }
    
    private void repain_all() {
        for (int i = 6; i >= 0; i--) {
            working_stacks[i].clear();
            Card c = game.get_working_stack(i, 0);
            int j = 0;
            while (!c.is_error_card()) {
                working_stacks[i].add_card(c, j++);
                c = game.get_working_stack(i, j);
            }
            working_stacks[i].my_repaint();
        }
        for (int i = 0; i < 4; i++) {
            color_stacks[i].clear();
            Card c = game.get_color_stack(i, 0);
            int j = 0;
            while (!c.is_error_card()) {
                color_stacks[i].add_card(c, j++);
                c = game.get_color_stack(i, j);
            }
            color_stacks[i].my_repaint();

        }
        Card c = game.get_hidden_deck(0);
        hidden_deck.clear();
        int j = 0;
        while (!c.is_error_card()) {
            hidden_deck.add_card(c, j++);
            c = game.get_hidden_deck(j);
        }
        hidden_deck.my_repaint();

        c = game.get_visible_deck(0);
        visible_deck.clear();
        j = 0;
        while (!c.is_error_card()) {
            visible_deck.add_card(c, j++);
            c = game.get_visible_deck(j);
        }
        visible_deck.my_repaint();
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