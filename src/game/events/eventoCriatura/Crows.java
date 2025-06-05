package game.events.eventoCriatura;

import game.events.Event;
import game.graphics.GamePanel;

public class Crows extends Event {
    private int sanityPoints;

    public Crows(double probability) {
        super("crows",
                "A bunch of crows\nappeared out of\n nowhere!\nVery weird...",
                probability);
    }

    public void execute(GamePanel gp) {
        gp.gameState = gp.dialogueState;
        gp.getPlayer().sanityPoints(sanityPoints);
        gp.getUi().setCurrentDialogue(getMessage());
    }
}
