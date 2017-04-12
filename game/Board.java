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
    /*
    Board load_game(std::string filename);
    bool save_game(Board game);
    Working_stack *get_stack(unsigned id) {
        return (id<7)?(&(working_stacks[id])):NULL;
    }
    bool fromC_toC(unsigned from, unsigned to);
    bool fromW_toC(unsigned from, unsigned to);
    void fromH_toV();
     */
}