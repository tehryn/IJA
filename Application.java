import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import src.gui.G_Board;
import src.game.Console;

import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.MatteBorder;
import src.game.Board;

public class Application extends JFrame implements ActionListener {
    private JMenuBar menu_bar = new JMenuBar();
    private G_Board[] games   = new G_Board[4];
    private boolean[] currently_open = new boolean[4];
    int x = 120/2;
    int y = 174/2;
    int step = 40/2;
    public Application() {
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(0,120,0));
        for(int i = 0; i < 4; i++) {
            games[i] = new G_Board(x, y, step);
            init_menu(i+1);
            currently_open[i] = false;
        }
        games[0].setBounds(0, 0, 80 + 7*x ,30+2*y+10*step);
        games[0].setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));
        games[1].setBounds(80 + 7*x, 0, 80 + 7*x, 30+2*y+10*step);
        games[1].setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));
        games[2].setBounds(0, 30+2*y+10*step, 80 + 7*x, 30+2*y+10*step);
        games[2].setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));
        games[3].setBounds(80 + 7*x, 30+2*y+10*step, 80 + 7*x, 30+2*y+10*step);
        games[3].setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));

        setTitle("Solitare by xmatej52 and xmisov00");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        set_layout();        
    }
    
    private void set_layout() {
        if (currently_open[1] || currently_open[2] || currently_open[3]) {
            setMinimumSize(new Dimension((80 + 7*x)*2+5, (32+2*y+11*step)*2));
        }
        else {
            setMinimumSize(new Dimension(80 + 7*x, 32+2*y+11*step));
        }
        for (int i = 0; i < 4; i++) {
            if (currently_open[i]) {
                games[i].repaint_all();
            }
        }
        repaint();
    }
    
    public void init_menu(int id){
        JMenu menu = new JMenu("Game" + id);
        menu.setMnemonic(KeyEvent.VK_S);

        JMenuItem menu_item = new JMenuItem("New game", KeyEvent.VK_N);
        menu_item.setActionCommand("new"+id);
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_item = new JMenuItem("Open", KeyEvent.VK_O);
        menu_item.setActionCommand("open"+id);
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_item = new JMenuItem("Save", KeyEvent.VK_S);
        menu_item.setActionCommand("save"+id);
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);
        
        menu_item = new JMenuItem("Leave game", KeyEvent.VK_L);
        menu_item.setActionCommand("leave"+id);
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);
        
        menu_item = new JMenuItem("Undo", KeyEvent.VK_Z);
        menu_item.setActionCommand("undo"+id);
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);
        
        menu_item = new JMenuItem("Hint", KeyEvent.VK_H);
        menu_item.setActionCommand("hint"+id);
        menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
        menu_item.addActionListener(this);
        menu.add(menu_item);

        menu_item = new JMenuItem("Exit program", KeyEvent.VK_Q);
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
        if (s.equals("quit")) {
            System.exit(0);
        }
        int id = -1;
        try {
            id += Integer.parseInt(s.substring(s.length()-1));
            if (id > 3) { return; }
        } catch (Exception exp) {
            return;
        }
        s = s.substring(0, s.length()-1);
        if (s.equals("new")) {
            games[id].new_game();
            if (!currently_open[id]) {
                currently_open[id] = true;
                add(games[id]);
                set_layout();
            }
        }
        else if (s.equals("open")) {
            JFileChooser c = new JFileChooser();
            c.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int rVal = c.showOpenDialog(this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
              games[id].load_game(c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName());
              if (!currently_open[id]) {
                  currently_open[id] = true;
                  add(games[id]);
                  set_layout();
              }
            }
        }
        else if (s.equals("save")) { 
            if (currently_open[id]) {
                JFileChooser c = new JFileChooser();
                c.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int rVal = c.showSaveDialog(this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    games[id].save_game(c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName());
                }
            }
        }
        else if (s.equals("leave")) {
            if (currently_open[id]) {
                currently_open[id] = false;
                remove(games[id]);
                set_layout();
            }
        }
        else if (s.equals("undo")) {
            if (currently_open[id]) {
                games[id].undo();
            }
        }
        else if (s.equals("undo")) {
            if (currently_open[id]) {
                games[id].hint();
            }
        }
      
    }
    public static void main(String [] args) {
        Application app = new Application();
        app.setVisible(true);
    }
}

