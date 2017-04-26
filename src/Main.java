import src.gui.G_Board;
import src.game.Console;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
//        Console bash = new Console();
//        Scanner stdin = new Scanner(System.in);
//        bash.start(stdin);
    G_Board game = new G_Board();
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run(){
            game.setVisible(true);
        }
    });
    }
}
