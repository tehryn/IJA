package game;
import game.Card;
import game.Card.Color;
import game.Card_stack;
import game.Single_color_stack;
import game.Working_stack;
import game.Card_deck_hidden;
import game.Card_deck_visible;

public class Board {
    protected Working_stack[] working_stacks    = new Working_stack[7];
    protected Single_color_stack[] color_stacks = new Single_color_stack[4];
    protected Card_deck_visible visible_deck;
    protected Card_deck_hidden hidden_deck;
    public Board() {}
    public void new_game() {
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

    public boolean fromW_toW(int from, int to, Card card) {
        if (from > 7 || to > 7 || to < 0 || from < 0) {
            return false;
        }
        Working_stack tmp = working_stacks[from].pop_until(card);
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
                return true;
            }
        }
        return true;
    }

    public boolean fromC_toC(int from, int to) {
        if (color_stacks[to].size() != 0 || color_stacks[from].size() != 1 || to > 3 || from > 3 || from < 0 || to < 0) {
            return false;
        }
        else {
            return color_stacks[to].push(color_stacks[from].pop());
        }
    }

    public boolean fromW_toC(int from, int to) {
        if (from >= 7 || to >= 4 || from < 0 || to < 0) {
            return false;
        }
        else {
            Card tmp = working_stacks[from].top();
            if (color_stacks[to].push(tmp)) {
                working_stacks[from].pop();
                working_stacks[from].set_top_visible();
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean fromC_toW(int from, int to) {
        if (from > 3 || to > 6 || from < 0 || to < 0) {
            return false;
        }
        else if (color_stacks[from].size() > 0) {
            Card tmp = color_stacks[from].pop();
            if (working_stacks[to].push(tmp)) {
                return true;
            }
            else {
                color_stacks[from].push(tmp);
                return false;
            }
        }
        else {
            return true;
        }
    }

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
    }

    /*
    Board load_game(std::string filename);
    bool save_game(Board game);
    Working_stack *get_stack(unsigned id) {
        return (id<7)?(&(working_stacks[id])):NULL;
    }
     */
}