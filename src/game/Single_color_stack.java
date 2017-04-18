package src.game;
import src.game.Card;
import src.game.Card.Color;
import src.game.Card_stack;

public class Single_color_stack extends Card_stack {
    Color color = Color.ERR;
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
    public Color get_color() {return this.color;}
}
