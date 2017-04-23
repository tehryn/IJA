/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   22. 4. 2017
 */
package src.game;
import src.game.Card;
import src.game.Card.Color;
import src.game.Card_stack;
import src.game.Single_color_stack;
import src.game.Working_stack;
import src.game.Card_deck_hidden;
import src.game.Card_deck_visible;
import src.game.History;
import src.game.Move;
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
 * player can do. <font color="red" ><b>DO NOT</b></font> use object of this class until you call method
 * new_game() or load_game(String filename).<br>
 * There are seven working stacks, IDs from 0 to 6,<br>
 * four color stacks, IDs from 0 to 3,<br>
 * one visible deck and one hidden deck. <br>
 * Object of Board also holds history of moves and current score.
 * @author Matejka Jiri (xmatej52)
*/
public class Board {
    /// @var Array of working stacks.
    protected Working_stack[] working_stacks    = new Working_stack[7];

    /// @var Array of color stacks.
    protected Single_color_stack[] color_stacks = new Single_color_stack[4];

    /// @var Deck of visible cards.
    protected Card_deck_visible visible_deck    = new Card_deck_visible();

    /// @var Deck of hidden cards.
    protected Card_deck_hidden hidden_deck      = new Card_deck_hidden();

    /// @var Score of game.
    protected int score = 0;

    /// @var History of moves
    protected History history = new History();

    /// @var If user asks for possible move, this variable tells if we should generate new vector.
    boolean generate = true;

    /// @var Vector of possible moves.
    History possible_moves;

    /**
     * Removes all cards from table and sets score to 0.
     */
    private void clear() {
        for (int i = 0; i < 7; i++) {
            working_stacks[i].clear();
        }
        for (int i = 0; i < 4; i++) {
            color_stacks[i].clear();
        }
        hidden_deck.clear();
        visible_deck.clear();
        history.clear();
        possible_moves.clear();
        score = 0;
        generate = true;
    }

    /**
     * Construct new game with no cards on table.
     */
    public Board() {
        for (int i = 0; i < 7; i++) {
            working_stacks[i] = new Working_stack();
        }
        for (int i = 0; i < 4; i++) {
            color_stacks[i] = new Single_color_stack();
        }
    }

    /**
     * Creates new game. If there was previous game, all cards will be removed,
     * and new cards will be dealt.
     */
    public void new_game() {
        clear();
        Card_stack pack_of_cards = Card_stack.new_deck();
        for (int i = 0; i < 7; i++) {
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
        clear();
        InputStream in;
        InputStreamReader input_reader;
        BufferedReader buffer_reader;
        try {
            in         = new FileInputStream(filename);
            input_reader  = new InputStreamReader(in, Charset.forName("ASCII"));
            buffer_reader = new BufferedReader(input_reader);
        } catch (Exception e) {
            return false;
        }
        String line;
        int lines = 0;
        int size;
        try {
            while ((line = buffer_reader.readLine()) != null) {
                if (lines == 13) {
                    try {
                        score = Integer.parseInt(line);
                        in.close();
                        return true;
                    } catch (Exception e) {
                        in.close();
                        clear();
                        return false;
                    }
                }
                if (line == "") {
                    lines++;
                    continue;
                }
                size = line.length();
                Card card;
                String c = "";
                for(int i = 0; i < size; i++) {
                    if ((i + 4) >= size || line.charAt(i+1) != '(' || line.charAt(i+3) != ')' || (line.charAt(i+4) != 'F' && line.charAt(i+4) != 'T')) {
                        in.close();
                        clear();
                        return false;
                    }
                    if (line.charAt(i) == 'L') {
                        c = "10" + line.substring(i+1, i+5);
                    }
                    else {
                        c = line.substring(i, i+5);
                    }
                    card = Card.string_to_card(c);
                    if (card.is_error_card()) {
                        in.close();
                        clear();
                        return false;
                    }
                    if (line.charAt(i+4) == 'F') {
                        card.make_hidden();
                    }
                    if (lines < 7) {
                        working_stacks[lines].force_push(card);
                    }
                    else if (lines < 11 ) {
                        color_stacks[lines-7].push(card);
                    }
                    else if (lines == 11) {
                        hidden_deck.force_push(card);
                    }
                    else if (lines == 12) {
                        visible_deck.force_push(card);
                    }
                    else {
                        in.close();
                        clear();
                        return false;
                    }
                    i+=4;
                }
                lines++;
            }
            in.close();
        } catch (Exception e) {
            return false;
        }
        clear();
        return false;
    }

    /**
     * Moves card(s) between two working packs. Moves all cards until specific
     * card is reached. When false was returned, no changes were made.
     * @param  from From wich pack cards will be taken.
     * @param  to   To which pack cards will be added.
     * @param  card Specific card used as a guard.
     * @return      True on succes, False on invalid operation.
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
     * Moves card between two color packs. Only ace can be moved. When false is
     * returned, no changes were made.
     * @param  from From which pack card will be taken.
     * @param  to   To which pack card will be added.
     * @return      True on succes, False on invalid operation.
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
     * Moves card from Working pack to Color pack. When false is returned, no
     * changes were made.
     * @param  from From which pack card will be taken.
     * @param  to   To which pack card will be added.
     * @return      True on succes, false on invalid operation.
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
     * Moves card from color pack to working pack. When false is returned, no
     * changes were made.
     * @param  from From which pack card will be taken.
     * @param  to   To which pack card will be added.
     * @return      True on succes, false on invalid operation.
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
        str += hidden_deck + "\n";
        str += visible_deck + "\n";
        str += score + "\n";
        return str;
    }

    /**
     * Converts whole game to string that can be used for saving.
     * @return String representing the game.
     */
    static public String toString(Board board) {
        String str = "";
        for (int i = 0; i < 7; i++) {
            str += Card_stack.toString(board.working_stacks[i]) + "\n";
        }
        for (int i = 0; i < 4; i++) {
            str += Card_stack.toString(board.color_stacks[i]) + "\n";
        }
        str += Card_stack.toString(board.hidden_deck) + "\n";
        str += Card_stack.toString(board.visible_deck) + "\n";
        str += board.score + "\n";
        return str;
    }

    /**
     * Represents move from visible deck to color stack. Pops card from visible
     * deck and pushes it into specific color stack.
     * @param  to ID of color stack where card will be pushed.
     * @return    True on succes otherwise false.
     */
    public boolean fromV_toC(int to) {
        if (to > -1 && to < 7 && color_stacks[to].push(visible_deck.top())) {
            visible_deck.pop();
            history.push(new Move(Move.Type.VC, 0, to, 1));
            score++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Represents move from visible deck to working stack. Pops card from visible
     * deck and pushes it into specific working stack.
     * @param  to ID of working stack where card will be pushed.
     * @return    True on succes otherwise false.
     */
    public boolean fromV_toW(int to) {
        if (to > -1 && to < 4 && working_stacks[to].push(visible_deck.top())) {
            visible_deck.pop();
            history.push(new Move(Move.Type.VW, 0, to, 1));
            score++;
            return true;
        }
        else {
            return false;
        }
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

    /**
     * Retrieve specific card from working stack
     * @param  id  ID of working stack.
     * @param  idx Index of card in working stack.
     * @return     Card from index on succes, otherwise returns invalid card
     *             (Color is set to ERR).
     */
    public Card get_working_stack(int id, int idx) {
        if (idx > -1 && idx < 7) {
            return working_stacks[id].get(idx);
        }
        else {
            return new Card();
        }
    }

    /**
     * Retrieve specific card from color stack
     * @param  id  ID of color stack.
     * @param  idx Index of card in color stack.
     * @return     Card from index on succes, otherwise returns invalid card
     *             (Color is set to ERR).
     */
    public Card get_color_stack(int id, int idx) {
        if (id > -1 && id < 4) {
            return color_stacks[id].get(idx);
        }
        else {
            return new Card();
        }
    }

    /**
     * Checks player won the game.
     * @return true when player won.
     */
    boolean is_victory() {
        boolean ret = true;
        for (int i = 0; i < 4; i++) {
            if (color_stacks[i].top().get_value() != 13) {
                ret = false;
            }
        }
        return ret;
    }
    
    /**
     * Retrieve card from hidden deck.
     * @param  idx Index of card in deck.
     * @return     Card from index on succes, otherwise returns invalid card
     *             (Color is set to ERR).
     */
    public Card get_hidden_deck(int idx) {
        return hidden_deck.get(idx);
    }

    /**
     * Retrieve card from visible deck.
     * @param  idx Index of card in deck.
     * @return     Card from index on succes, otherwise returns invalid card
     *             (Color is set to ERR).
     */
    public Card get_visible_deck(int idx) {
        return visible_deck.get(idx);
    }

    /**
     * Retrieve score of game.
     * @return value of scor.
     */
    public int get_score() {
        return score;
    }
}