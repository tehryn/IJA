import game.Board;

public class Main {
    public static void main(String [] args) {
        Board b = new Board();
        b.new_game();
//        System.out.println(b + "\n--------------------------------------");
        b.save_game("test.txt");
        Board c = new Board();
        c.load_game("play-test.txt");
        System.out.println(c);
    }
}