package src.gui;

import src.game.Board;
import src.game.Card;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class G_Board extends JLayeredPane {
//    private JMenuBar menu_bar        = new JMenuBar(); // reprezentuje horni ovladaci listu
    private G_Working_stack[] working_stacks    = new G_Working_stack[7];
    private G_Single_color_stack[] color_stacks = new G_Single_color_stack[4];
    private G_Card_deck_visible visible_deck;
    private G_Card_deck_hidden  hidden_deck;
    private boolean proc_move = false;
    private int from_id;
    private int to_id;
    private Card moved_card;
    private int x;
    private int y;
    private int step;
    private Stack to_type;
    private Stack from_type;

    public static enum Stack {
        W, C, H, V, ERR;
    }

    private Board game = new Board();

    public G_Board(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
        setLayout(null);
        setBackground(new Color(0,120,0));
        setOpaque(true);
        visible_deck = new G_Card_deck_visible(x, y);
        hidden_deck  = new G_Card_deck_hidden(x, y);
        hidden_deck.setBounds(10, 10, x, y);
        visible_deck.setBounds(20+x, 10, x, y);
        hidden_deck.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked_on_hidden(e);
                }
            });
        visible_deck.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked_on_visible(e);
                }
            });
        add(hidden_deck);
        add(visible_deck);
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new G_Single_color_stack(x, y);
            color_stacks[i].setBounds(40+3*x + i*(10+x), 10, x, y);
            color_stacks[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked_on_color(e);
                }
            });
            add(color_stacks[i]);
        }
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new G_Working_stack(x, y, step);
            working_stacks[i].setBounds(10 + i*(10+x), y + 20, x, y+step*19);
            working_stacks[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked_on_working(e);
                }
            });
            add(working_stacks[i]);
        }
        setMinimumSize(new Dimension(80 + 7*x, 30+2*y+19*step));
        repaint_all();
    }
    
    public void save_game(String string) {
        game.save_game(string);
    }

    public void new_game() {
        game.new_game();
        repaint_all();
    }

    public void load_game(String string) {
        game.load_game(string);
        repaint_all();
    }    
    
    public void undo() {
        this.game.undo();
        repaint_all();
    }
    
    public void hint() {
        // TODO
    }
    
    private void clicked_on_hidden(MouseEvent e) {
        if (proc_move) {
            to_type   = Stack.ERR;
            move();
        }
        else {
            from_type = Stack.H;
            move();        
        }
    }
    
    private void clicked_on_visible(MouseEvent e) {
        if (proc_move) {
            to_type   = Stack.ERR;
            move();
        }
        else {
            from_type = Stack.V;
            visible_deck.set_border();
            proc_move = true;
        }
    }
    
    private void clicked_on_color(MouseEvent e) {
        for (int i = 0; i < 4; i++) {
            if (e.getSource() == color_stacks[i]) {
                if (proc_move) {
                    to_id = i;
                    to_type = Stack.C;
                    move();
                }
                else {
                    from_id = i;
                    from_type = Stack.C;
                    color_stacks[i].set_border();
                    proc_move = true;
                }               
                break;
            }
        }
    }
    
    private void clicked_on_working(MouseEvent e) {
        for (int i = 0; i < 7; i++) {
            if (e.getSource() == working_stacks[i]) {
                if (proc_move) {
                    to_id = i;
                    to_type = Stack.W;
                    move();
                }
                else {
                    from_id = i;
                    from_type = Stack.W;
                    moved_card = working_stacks[i].get_card(e.getY());
                    if (moved_card.is_error_card() || !moved_card.is_visible()) {
                        return;
                    }
                    working_stacks[i].set_border(moved_card);
                    proc_move = true;
                }     
                break;
            }
        }       
    }
    
    private void move() {
        if (to_type == Stack.ERR) {
            switch(from_type) {
                case H:
                    hidden_deck.unset_border();
                    break;
                case V:
                    visible_deck.unset_border();
                    break;
                case C:
                    color_stacks[from_id].unset_border();
                    break;
                case W:
                    working_stacks[from_id].unset_border(moved_card);
                    break;
                default: break;
            }
        }
        switch (from_type) {
            case H:
                game.fromH_toV();
                repaint_hidden();
                repaint_visible();
                break;
            case V:
                if (to_type == Stack.C) {
                    if (game.fromV_toC(to_id)) {
                        repaint_color(to_id);
                        repaint_visible();
                    }
                    else {
                        visible_deck.unset_border();
                    }
                }
                else if (to_type == Stack.W) {
                    if (game.fromV_toW(to_id)) {
                        repaint_working(to_id);
                        repaint_visible();                        
                    }
                    else {
                        visible_deck.unset_border();
                    }
                }
                break;
            case C:
                if(to_type == Stack.C) {
                    if (game.fromC_toC(from_id, to_id)) {
                        repaint_color(from_id);
                        repaint_color(to_id);
                    }
                    else {
                        color_stacks[from_id].unset_border();
                    }
                }
                else if (to_type == Stack.W) {
                    if (game.fromC_toW(from_id, to_id)) {
                        repaint_color(from_id);
                        repaint_working(to_id);                        
                    }
                    else {
                        color_stacks[from_id].unset_border();
                    }
                }
                break;
            case W:
                if(to_type == Stack.C) {
                    if (game.fromW_toC(from_id, to_id)){
                        repaint_working(from_id);
                        repaint_color(to_id);
                    }
                    else {
                        working_stacks[from_id].unset_border(moved_card);
                    }
                }
                else if(to_type == Stack.W) {
                    if (game.fromW_toW(from_id, to_id, moved_card)) {
                        repaint_working(from_id);
                        repaint_working(to_id);
                    }
                    else {
                        working_stacks[from_id].unset_border(moved_card);
                    }
                }
            default: break;
        }
        proc_move = false;
    }
    
    private void repaint_hidden() {
        Card c = game.get_hidden_deck(0);
        hidden_deck.clear();
        int j = 0;
        while (!c.is_error_card()) {
            hidden_deck.add_card(c, j++);
            c = game.get_hidden_deck(j);
        }
        hidden_deck.my_repaint();
        repaint();
    }
    
    private void repaint_visible() {
        Card c = game.get_visible_deck(0);
        visible_deck.clear();
        int j = 0;
        while (!c.is_error_card()) {
            visible_deck.add_card(c, j++);
            c = game.get_visible_deck(j);
        }
        visible_deck.my_repaint();
        repaint();
    }
    
    private void repaint_working(int i) {
        Card c = game.get_working_stack(i, 0);
        working_stacks[i].clear();
        int j = 0;
        while (!c.is_error_card()) {
            working_stacks[i].add_card(c, j++);
            c = game.get_working_stack(i, j);
       }
        if (j == 0) {
            working_stacks[i].setBounds(10 + i*(10+x), y + 20, x, y);
        }
        else {
            working_stacks[i].setBounds(10 + i*(10+x), y + 20, x, y+step*19);
        }
        working_stacks[i].my_repaint();
        repaint();
    }
    
    private void repaint_color(int i) {
        color_stacks[i].clear();
        Card c = game.get_color_stack(i, 0);
        int j = 0;
        while (!c.is_error_card()) {
            color_stacks[i].add_card(c, j++);
            c = game.get_color_stack(i, j);
        }
        color_stacks[i].my_repaint();
        repaint();
    }
    
    public void repaint_all() {
        for (int i = 6; i >= 0; i--) {
            repaint_working(i);
        }
        for (int i = 0; i < 4; i++) {
            repaint_color(i);
        }
        repaint_hidden();
        repaint_visible();
    }
}