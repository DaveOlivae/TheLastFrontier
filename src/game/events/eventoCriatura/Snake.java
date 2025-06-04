package game.events.eventoCriatura;

import game.events.Event;
import game.graphics.GamePanel;

public class Snake extends Event {
    private int damage = 20;

    public Snake(double probability) {
        super("snake",
                "A wild snake crawled out of\nthe grass and bit you!\nYou took 20 points of damage.",
                probability);
    }

    public void execute(GamePanel gp) {
        gp.getPlayer().damage(damage);
        gp.gameState = gp.dialogueState;
        gp.getUi().setCurrentDialogue(getMessage());
    }
}
