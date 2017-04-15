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
        Card popped = this.pop();
        Card on_top = this.top();
        Working_stack new_stack = new Working_stack();
        Working_stack err_stack = new Working_stack();
        new_stack.force_push(popped);
        while (!popped.is_similar(on_top) && popped != card && popped.is_visible()) {
            popped = this.pop();
            on_top = this.top();
            new_stack.insert_bottom(popped);
        }
        if (popped == card) {
            return new_stack;
        }
        else {
            for (int i = 0; i < new_stack.size(); i++) {
                force_push(new_stack.pop_bottom());
            }
            return err_stack;
        }
    }
}