package game.events.eventoClimatico;

import game.graphics.GamePanel;

public class Blizzard extends ClimateEvent {
    public Blizzard() {
        super("Nevasca",
                "Uma forte tempestade de neve",
                "Nevasca",
                3,
                "Frio");
    }

    public void execute(GamePanel gp) {

    }
}
