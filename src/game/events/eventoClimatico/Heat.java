package game.events.eventoClimatico;

import game.graphics.GamePanel;

public class Heat extends ClimateEvent {
    public Heat() {
        super("Calor Extremo",
                "O calor está insuportável, você está ficando com mais sede",
                "Quente",
                3,
                "Sede");
    }

    public void execute(GamePanel gp) {

    }
}
