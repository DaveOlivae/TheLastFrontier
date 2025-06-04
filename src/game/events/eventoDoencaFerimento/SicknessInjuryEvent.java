package game.events.eventoDoencaFerimento;

import game.events.Event;
import game.graphics.GamePanel;

public abstract class SicknessInjuryEvent extends Event {

    public SicknessInjuryEvent(String name, String message, double probability) {
        super(name, message, probability);
    }

    public abstract void update(GamePanel gp);
}
