package game.events.eventoCriatura;

import game.events.Event;
import game.graphics.GamePanel;

public class Crows extends Event {
    public Crows(double probability) {
        super("crows",
                "A bunch of crows\nappeared out of\n nowhere!\nVery weird...",
                probability);
    }

    public void execute(GamePanel gp) {

    }
}
