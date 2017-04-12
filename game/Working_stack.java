package game;
import game.Card;
import game.Card.Color;
import game.Card_stack;

public class Working_stack extends Card_stack {
    public boolean push(Working_stack stack) {
        Card card = stack.bottom();
        if ((((this.top().get_value() - 1) == card.get_value()) && !card.is_similar(this.top()))
             || (this.size() == 0 && (card.get_value() == 13))) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                force_push(stack.pop_bottom());
            }
            return true;
        }
        else {
            return false;
        }
    }
    public boolean push(Card card) {
        if (this.size() == 0 && card.get_value() == 13) {
            force_push(card);
            return true;
        }

        if ((this.top().get_value() - 1) == card.get_value() && !card.is_similar(this.top())) {
            force_push(card);
            return true;
        }
        else {
            return false;
        }
    }
    public Working_stack pop_until(Card card) {
        int i = -1;
        for(i = this.size() - 1; i >= 0; i--) {
                if (card == this.stack.get(i) && this.stack.get(i).is_visible()) {
                    break;
                }
        }
        if (i < 0 || !this.stack.get(i).is_visible()) {
            Working_stack empty = new Working_stack();
            return empty;
        }
        Card compare_to = this.top();
        Working_stack new_stack = new Working_stack();
        while (!(card == compare_to)) {
            compare_to = pop();
            new_stack.insert_bottom(compare_to);
        }
        return new_stack;
    }
}