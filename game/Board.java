package game;
import game.Card;
import game.Card.Color;
import game.Card_stack;
import game.Single_color_stack;
import game.Working_stack;
import game.Card_deck_hidden;
import game.Card_deck_visible;
import game.History;
import game.Move;
import java.util.Vector;
import java.io.Writer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.io.PrintWriter;

/**
 * Class representing table with cards. Methods represents all possible moves,
 * player can do. DO NOT use object of this class until you call method
 * new_game() or load_game(String filename).
 */
public class Board {
    protected Working_stack[] working_stacks    = new Working_stack[7];
    protected Single_color_stack[] color_stacks = new Single_color_stack[4];
    protected Card_deck_visible visible_deck    = new Card_deck_visible();
    protected Card_deck_hidden hidden_deck      = new Card_deck_hidden();
    protected int score = 0;
    protected History history = new History();
    public Board() {}
    /**
     * Creates new game. If there was previous game, all cards will be removed,
     * and new cards will be dealt.
     */
    public void new_game() {
        Card_stack pack_of_cards = Card_stack.new_deck();
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new Working_stack();
            for (int j = 0; j < i + 1; j++) {
                working_stacks[i].force_push(pack_of_cards.pop_random());
            }
            working_stacks[i].set_top_visible();
        }
        Card tmp = pack_of_cards.pop_random();
        while (tmp.get_color() != Color.ERR) {
            hidden_deck.force_push(tmp);
            tmp = pack_of_cards.pop_random();
        }
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new Single_color_stack();
        }
    }

    /**
     * Save current progress of game into file.
     * @param  filename Name of file, where game will be stored
     * @return          True if game was saved, false on error.
     */
    public boolean save_game(String filename) {
        try {
            PrintWriter output = new PrintWriter(filename, "ASCII");
            output.println(this);
            output.flush();
            output.close();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Loads previous saved game from file.
     * @param  filename File where game is stored.
     * @return True of game was loaded, false on error.
     */
    public boolean load_game(String filename) {
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new Working_stack();
        }
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new Single_color_stack();
        }
        hidden_deck  = new Card_deck_hidden();
        visible_deck = new Card_deck_visible();
        String line;
        int lines = 0;
        try {
            InputStream input = new FileInputStream(filename);
            InputStreamReader input_reader = new InputStreamReader(input, Charset.forName("ASCII"));
            BufferedReader buffer_reader = new BufferedReader(input_reader);
            while ((line = buffer_reader.readLine()) != null) {
                if (lines < 13) {
                    int size = line.length();
                    String tmp = "";
                    int value = 0;
                    Card card;
                    char c = 'E';
                    for(int i = 0; i < size; i++) {
                        if (line.charAt(i) == '\n') {
                            break;
                        }
                        else if (line.charAt(i) == '(') {
                            value = Integer.parseInt(tmp);
                        }
                        else if (line.charAt(i) == ')') {
                            tmp = "";
                            if (c == 'C') {
                                card = new Card(value, Color.CLUBS);
                            }
                            else if (c == 'D') {
                                card = new Card(value, Color.DIAMONDS);
                            } else if (c == 'H') {
                                card = new Card(value, Color.HEARTS);
                            } else if (c == 'S') {
                                card = new Card(value, Color.SPADES);
                            }
                            else {
                                input.close();
                                return false;
                            }
                            i++;
                            if (line.charAt(i) == 'T') {
                                card.make_visible();
                            }
                            switch(lines) {
                                /*from 0 to 6 - reading working_stacks*/
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                    working_stacks[lines].force_push(card);
                                    break;
                                /* from 7 to 10 - reading color_stacks*/
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                    color_stacks[lines].force_push(card);
                                    break;
                                /* reading hidden_deck */
                                case 11:
                                    hidden_deck.force_push(card);
                                    break;
                                /* reading visible_deck */
                                case 12:
                                    visible_deck.force_push(card);
                            }
                        }
                        else {
                            tmp += line.charAt(i);
                            c    = line.charAt(i);
                        }
                    }
                }
                else {
                    this.score = Integer.parseInt(line);
                }
                lines++;
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    /**
     * Represents move between two working stacks.
     * @param  from ID of working stack from which cards will be taken.
     * @param  to   ID of working stack to which cards will be added.
     * @param  card Card on which user clicked.
     * @return      True on succes, false on ilegal operation.
     */
    public boolean fromW_toW(int from, int to, Card card) {
        if (from > 7 || to > 7 || to < 0 || from < 0) {
            return false;
        }
        Working_stack tmp = working_stacks[from].pop_until(card);
        int card_count = tmp.size();
        if (tmp.size() > 0) {
            if (!working_stacks[to].push(tmp)) {
                int size = tmp.size();
                for (int i = 0; i < size; i++) {
                    working_stacks[from].force_push(tmp.pop_bottom());
                }
                return false;
            }
            else {
                working_stacks[from].set_top_visible();
                score++;
                history.push(new Move(Move.Type.WW, from, to, card_count));
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Represents move between two color stacks (Only aces can be moved).
     * @param  from ID of color stack from which ace will be removed.
     * @param  to   ID of color stack to which ace will be added.
     * @return      True on succes, false on ilegal operation.
     */
    public boolean fromC_toC(int from, int to) {
        if (color_stacks[to].size() != 0 || color_stacks[from].size() != 1 || to > 3 || from > 3 || from < 0 || to < 0) {
            return false;
        }
        else {
            score++;
            history.push(new Move(Move.Type.CC, from, to, 1));
            return color_stacks[to].push(color_stacks[from].pop());
        }
    }

    /**
     * Represents move from working stack to color stack. Only one card can be
     * moved.
     * @param  from ID of working stack from which card will be taken.
     * @param  to   ID of color stack to which card will be added.
     * @return      True on succes, false on ilegal operation.
     */
    public boolean fromW_toC(int from, int to) {
        if (from >= 7 || to >= 4 || from < 0 || to < 0) {
            return false;
        }
        else {
            Card tmp = working_stacks[from].top();
            if (color_stacks[to].push(tmp)) {
                working_stacks[from].pop();
                working_stacks[from].set_top_visible();
                score++;
                history.push(new Move(Move.Type.WC, from, to, 1));
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Represents move from color stack to working stack. Only one card can be
     * moved.
     * @param  from ID of color stack from which card will be removed.
     * @param  to   ID of working stack to which card will be added.
     * @return      True on succes, false on ilegal operation.
     */
    public boolean fromC_toW(int from, int to) {
        if (from > 3 || to > 6 || from < 0 || to < 0) {
            return false;
        }
        else if (color_stacks[from].size() > 0) {
            Card tmp = color_stacks[from].pop();
            if (working_stacks[to].push(tmp)) {
                score++;
                history.push(new Move(Move.Type.CW, from, to, 1));
                return true;
            }
            else {
                color_stacks[from].push(tmp);
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Represents click on hidden deck. Removes one card from it and adds it to
     * visible deck. If hidden deck is empty, takes all cards from visible deck
     * and moves them to hidden deck.
     */
    public void fromH_toV() {
        if (hidden_deck.size() == 0) {
            int size = hidden_deck.size();
            for (int i = 0; i < size; i++) {
                Card tmp = visible_deck.pop();
                tmp.make_hidden();
                hidden_deck.force_push(tmp);
            }
        }
        else {
            Card tmp = hidden_deck.pop();
            tmp.make_visible();
            visible_deck.force_push(tmp);
        }
        score++;
        history.push(new Move(Move.Type.H, 0, 0, 1));
    }

    /**
     * Converts whole game to string.
     * @return String representing the game.
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < 7; i++) {
            str += working_stacks[i] + "\n";
        }
        for (int i = 0; i < 4; i++) {
            str += color_stacks[i] + "\n";
        }
        str += hidden_deck;
        str += visible_deck;
        str += score;
        return str;
    }

    /**
     * Undo operation. Can be used multipe times. If game is loaded, undo cannot
     * be done to states of game that was before saving.
     */
    public void undo() {
        Move move = history.pop();
        Card tmp;
        switch(move.get_type()) {
            case ERR: break;
            case WW:
                Working_stack new_stack = new Working_stack();
                for(int i = 0; i < move.get_count(); i++) {
                    tmp = working_stacks[move.get_to()].pop();
                    new_stack.insert_bottom(tmp);
                }
                for(int i = 0; i < move.get_count(); i++) {
                    working_stacks[move.get_from()].force_push(new_stack.pop_bottom());
                }
                break;
            case WC:
                tmp = color_stacks[move.get_to()].pop();
                working_stacks[move.get_from()].force_push(tmp);
                break;
            case CW:
                tmp = working_stacks[move.get_to()].pop();
                color_stacks[move.get_from()].force_push(tmp);
                break;
            case CC:
                tmp = color_stacks[move.get_to()].pop();
                color_stacks[move.get_from()].force_push(tmp);
                break;
            case VW:
                tmp = working_stacks[move.get_to()].pop();
                visible_deck.force_push(tmp);
                break;
            case VC:
                tmp = color_stacks[move.get_to()].pop();
                visible_deck.force_push(tmp);
                break;
            case  H:
                tmp = visible_deck.pop();
                hidden_deck.force_push(tmp);
        }
        score--;
    }
}