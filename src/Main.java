import src.gui.G_Board;
import src.game.Console;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
//    Console bash = new Console();
//    Scanner stdin = new Scanner(System.in);
//    bash.start(stdin);
//        Board b = new Board();
//        b.new_game();
//        System.out.println(b + "\n--------------------------------------");
//        b.save_game("../examples/test.txt");
//        Board c = new Board();
//        c.load_game("../examples/play-test.txt");
//        System.out.println(c);
        G_Board game = new G_Board();
        game.setVisible(true);
    }
}
