package game.events.eventoCriatura;

import game.events.Event;
import game.graphics.GamePanel;

public class Wolf extends Event {
    private int life = 50;
    private int attack = 30;

    public Wolf(double probability) {
        super("worlf",
                "A wild wolf appeared!",
                probability);
    }

    public void execute(GamePanel gp) {

    }
}
