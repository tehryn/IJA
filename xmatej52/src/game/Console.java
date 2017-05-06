/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   22. 4. 2017
 * content: Console version of game.
 */
package src.game;
import src.game.Card;
import src.game.Board;
import src.game.Move;
import java.util.Scanner;
/**
 * Class representing text inviroment of game. All commands can be entered by
 * input stream operator. Class will load all commands until eof is reached. <br>
 * Usage of this class: <br>
 * Command [FILENAME] [ [ [ [ [ Card_stack1 ] FROM ] Card_stack2 TO ] CARD ] ]<br>
 * Command1 is one of following strings:<br>
 * print - prints game on stdout<br>
 * move  - moves card(s) from Card_stack1 with index FROM to Card_stack2 with index TO until CARD is reached<br>
 * load  - loads game that is saved in file FILENAME<br>
 * save  - saves game into file FILENAME<br>
 * new   - creates new game<br>
 * hint  - display one possible move<br>
 * help  - prints help on stdout<br>
 * FILENAME is string representing path to file.<br>
 * Card_stacks represent working (W), color (C), hidden (H) or visible (V) deck.<br>
 * Card_stack1 represents deck from which card(s) will be taken<br>
 * Card_stack2 represents deck to which card(s) will be added<br>
 * FROM is ID of Card_stack1 (0-6 when Card_stack1 is working, 0-3 when Card_stack1 is colour)<br>
 * TO is ID of same values as FROM, but represents ID of Card_stack2<br>
 * CARD is only used when moving card(s) between 2 working stacks<br>
 * and all cards until CARD is reached will be moved between them<br>
 * @author Matejka Jiri (xmatej52)
 */
public class Console {
    /// @var Variable representing game.
    public Board game = new Board();

    /// @var Variable representing invalid card.
    public static Card err;

    /**
     * Process command and return, if command was valid. Most of parameters
     * are optional. If function can decide what to move, function will do it.
     * Function only reads parameters that are useful, for example it only reads
     * card parameter when user wants to move between two working stacks.
     * @param  cmd1 definition of command (move, print, help, ...).
     * @param  cmd2 filename or source stack.
     * @param  from id of source stack
     * @param  cmd3 destination stack
     * @param  to   id of destination stack
     * @param  card Card that will be moved (only used when moving between 2 stacks)
     * @return      True on success otherwise returns false.
     */
    public boolean command(String cmd1, String cmd2, int from, String cmd3, int to, Card card) {
        boolean ret = false;
        if (cmd1.equals("print")) {
            ret = true;
        }
        else if (cmd1.equals("move")) {
            if (cmd2.equals("H") || cmd2.equals("hidden")) {
                game.fromH_toV();
                ret = true;
            }
            else if (cmd2.equals("V") || cmd2.equals("visible")) {
                if (cmd3.equals("W") || cmd3.equals("working")) {
                    ret = game.fromV_toW(to);
                }
                else if (cmd3.equals("C") || cmd3.equals("color")) {
                    ret = game.fromV_toC(to);
                }
            }
            else if (cmd2.equals("W") || cmd2.equals("working")) {
                if(cmd3.equals("W") || cmd3.equals("working")) {
                    ret = game.fromW_toW(from, to, card);
                }
                else if (cmd3.equals("C") || cmd3.equals("color")) {
                    ret = game.fromW_toC(from, to);
                }
            }
            else if (cmd2.equals("C") || cmd2.equals("color")) {
                if (cmd3.equals("C") || cmd3.equals("color")) {
                    ret = game.fromC_toC(from, to);
                }
                else if (cmd3.equals("W") || cmd3.equals("working")) {
                    ret = game.fromC_toW(from, to);
                }
            }
        }
        else if (cmd1.equals("hint")) {
            Move tmp = game.help();
            if (!tmp.is_move_invalid()) {
                System.out.print("move " + tmp);
            }
            return true;
        }
        else if (cmd1.equals("undo")) {
            game.undo();
            ret = true;
        }
        else if (cmd1.equals("load")) {
            ret = game.load_game(cmd2);
        }
        else if (cmd1.equals("save")) {
            ret = game.save_game(cmd2);
        }
        else if (cmd1.equals("new")) {
            game.new_game();
            ret = true;
        }
        else if (cmd1.equals("help")) {
            help();
            return true;
        }
        if (ret) {
            System.out.print(game);
        }
        if (game.is_victory()) {
            System.out.println("You won the game!");
            System.out.println("But you can continue playing...");
        }
        return ret;
    }

    /**
     * Parse string and call command function.
     * @param  str String representing command.
     * @return     Returns value of command function.
     */
    public boolean string_to_command(String str) {
        int size = str.length();
        String cmd1 = "", cmd2 = "", cmd3 = "", tmp = "";
        int to = 10, from = 10;
        Card card = new Card();
        int arg_id = 0;
        for (int i = 0; i < size; i++) {
            switch(arg_id) {
                case 0:
                    if (str.charAt(i) == ' ') {
                        arg_id++;
                    }
                    else {
                        cmd1 += str.charAt(i);
                    }
                    break;
                case 1:
                    if (str.charAt(i) == ' ') {
                        arg_id++;
                    }
                    else {
                        cmd2 += str.charAt(i);
                    }
                    break;
                case 2:
                    if (Character.isDigit(str.charAt(i))) {
                        from = str.charAt(i++) - '0';
                        arg_id++;
                    }
                    else {
                        i--;
                        arg_id++;
                    }
                    break;
                case 3:
                    if (str.charAt(i) == ' ') {
                        arg_id++;
                    }
                    else {
                        cmd3 += str.charAt(i);
                    }
                    break;
                case 4:
                    to = str.charAt(i++) - '0';
                    arg_id++;
                    break;
                case 5: tmp += str.charAt(i);
            }
        }
        card = Card.string_to_card(tmp);
        return command(cmd1, cmd2, from, cmd3, to, card);
    }

    /**
     * Creates new console application.
     */
    public Console() {}

    /**
     * Prints help on stdout.
     */
    public void help() {
        System.out.println("Use: Command [FILENAME] [ [ [ [ [ Card_stack1 ] FROM ] Card_stack2 TO ] CARD ] ]\n");
        System.out.println("Command1 is one of following strings:");
        System.out.println("print - prints game on stdout");
        System.out.println("move  - moves card(s) from Card_stack1 with index FROM to Card_stack2 with index TO until CARD is reached");
        System.out.println("load  - loads game that is saved in file FILENAME");
        System.out.println("save  - saves game into file FILENAME");
        System.out.println("new   - creates new game");
        System.out.println("help  - prints this message to stdout");
        System.out.println("hint  - display one possible move");
        System.out.println("");
        System.out.println("FILENAME is string representing path to file.");
        System.out.println("Card_stacks represent working (W), color (C), hidden (H) or visible (V) deck.");
        System.out.println("Card_stack1 represents deck from which card(s) will be taken");
        System.out.println("Card_stack2 represents deck to which card(s) will be added");
        System.out.println("FROM is ID of Card_stack1 (0-6 when Card_stack1 is working, 0-3 when Card_stack1 is color)");
        System.out.println("TO is ID of same values as FROM, but represents ID of Card_stack2");
        System.out.println("CARD is only used when moving card(s) between 2 working stacks");
        System.out.println("and all cards until CARD is reached will be moved between them");
    }

    /**
     * Converts current game into string.
     * @return String representing current game.
     */
    public String toString() {
        return "" + game;
    }

    /**
     * Read input scanner until end is reached.
     * @param in Input scanner
     */
    public void start(Scanner in) {
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            if (!string_to_command(line)) {
                if (line != "") {
                    System.err.println("Invalid move or command '" + line + "'\n");
                }
            }
        }
    }
}
