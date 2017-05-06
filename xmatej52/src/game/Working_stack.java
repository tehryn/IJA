/*
 * Author: Matejka Jiri
 * login:  xmatej52
 * school: VUT FIT
 * date:   23. 4. 2017
 */
package src.game;
import src.game.Card;
import src.game.Card.Color;
import src.game.Card_stack;


/**
 * Class representing stack of card with multiple colors.
 * @author Matejka Jiri (xmatej52)
 */
public class Working_stack extends Card_stack {

    /**
     * Pushes stack of cards into stack.
     * @param  stack Stack of cards tha will be pushed.
     * @return       True on succes, false on invalid operation.
     */
    public boolean push(Working_stack stack) {
        Card card = stack.bottom();
        if ((((this.top().get_value() - 1) == card.get_value()) && !card.is_similar(this.top()))
             || (this.size() == 0 && (card.get_value() == 13)) && !card.is_error_card()) {
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

     /**
      * Pushes card into stack.
      * @param  card Card that will be pushed.
      * @return      True on succes, false on invalid operation.
      */
    public boolean push(Card card) {
        if (this.size() == 0 && card.get_value() == 13) {
            force_push(card);
            return true;
        }

        if (card.is_visible() && ((this.top().get_value() - 1) == card.get_value() && !card.is_similar(this.top()))) {
            force_push(card);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Pops all card until given card is reached.
     * @param  card Card that will be used as guard
     * @return      All cards including given card as stack, when given card
     *              is not in stack or is not visible, empty stack is returned.
     */
    public Working_stack pop_until(Card card) {
        Working_stack err_stack = new Working_stack();
        if (!card.is_visible()) {
            return err_stack;
        }
        Card popped = this.pop();
        Card on_top = this.top();
        Working_stack new_stack = new Working_stack();
        new_stack.force_push(popped);
        while (!popped.is_similar(on_top) && !popped.equals(card) && popped.is_visible() && !on_top.is_error_card()) {
            popped = this.pop();
            on_top = this.top();
            new_stack.insert_bottom(popped);
        }
        if (popped.equals(card)) {
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