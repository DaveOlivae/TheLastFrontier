package game.events.eventoClimatico;

import game.graphics.GamePanel;

public class Rain extends ClimateEvent {
    public Rain() {
        super("Chuva Forte",
                "Está caindo uma chuva torrencial, melhor encontrar abrigo",
                "Úmido",
                4,
                "Exploração");
    }

    public void execute(GamePanel gp) {

    }
}
