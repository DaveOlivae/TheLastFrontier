package game.events.eventoDoencaFerimento;

import game.graphics.GamePanel;

public class Infection extends SicknessInjuryEvent {
    public Infection() {
        super("Infecção",
                "Você está se sentido doente, melhor tomar remédio.",
                "Infecção",
                "Vida, Energia",
                "Remédio");
    }

    public void execute(GamePanel gp) {

    }
}
