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
 * Class representing stack of colour cards with same colour.
 * @author Matejka Jiri (xmatej52)
 */
public class Single_color_stack extends Card_stack {

    /// @var color of this stack. Color will be changed by pushing Ace into stack.
    Color color = Color.ERR;

    /**
     * Pushes card into stack.
     * @param card Card that will be pushed.
     * @return     True on success, otherwise False.
     */
    public boolean push(Card card) {
        if (this.size() == 0) {
            if (card.get_value() != 1) {
                return false;
            }
            else {
                this.color = card.get_color();
                this.force_push(card);
                return true;
            }
        }
        else if (card.get_color() == this.color && card.get_value() == (1 + this.top().get_value())) {
            this.force_push(card);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Retrieve colour of this stack.
     * @return Colour of stack.
     */
    public Color get_color() {return this.color;}
}
