package src.gui;

import src.game.Board;
import src.game.Card;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class G_Board extends JFrame implements ActionListener {
//    private JMenuBar menu_bar        = new JMenuBar(); // reprezentuje horni ovladaci listu
    private G_Working_stack[] working_stacks    = new G_Working_stack[7];
    private G_Single_color_stack[] color_stacks = new G_Single_color_stack[4];
    private G_Card_deck_visible visible_deck;
    private G_Card_deck_hidden  hidden_deck;
    private JMenuBar menu_bar = new JMenuBar();

    private Board game = new Board();

    public G_Board() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(0,120,0));
        int x = 120/2;
        int y = 174/2;
        int step = 20;
        init_menu();
        //add(menu_bar);
        visible_deck = new G_Card_deck_visible(x, y);
        hidden_deck  = new G_Card_deck_hidden(x, y);
        hidden_deck.setBounds(10, 10, x, y);
        visible_deck.setBounds(20+x, 10, x, y);
        add(hidden_deck);
        add(visible_deck);
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new G_Single_color_stack(x, y);
            color_stacks[i].setBounds(40+3*x + i*(10+x), 10, x, y);
            add(color_stacks[i]);
        }
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new G_Working_stack(x, y, step);
            working_stacks[i].setBounds(10 + i*(10+x), y + 20, x, y+step*19);
            add(working_stacks[i]);
        }
        setMinimumSize(new Dimension(80 + 7*x, 2*y + 30 + 2*y + step*5));
        setTitle("Solitare by xmatej52 and xmisov00");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //pack();
        game.load_game("saved.txt");
        //System.out.println(game);
        repaint_all();
    }
    
    private void repaint_all() {
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
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("new")) {
            game.new_game();
            System.out.print(game);
            repaint_all();
        }
        else if (s.equals("open")) {
            JTextField filename = new JTextField();
            JTextField dir = new JTextField();
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
              filename.setText(c.getSelectedFile().getName());
              dir.setText(c.getCurrentDirectory().toString());
            }
            game.load_game("../examples/test.txt"); //TODO
            repaint_all();
        }
        else if (s.equals("save")) {
            game.save_game("../examples/test2.txt"); //TODO
            repaint_all();
        }
        else if (s.equals("quit")) {
            System.exit(0);
        }
    }
}