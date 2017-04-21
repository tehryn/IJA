import src.game.Board;
import src.gui.G_Board;

public class Main {
    public static void main(String [] args) {
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
