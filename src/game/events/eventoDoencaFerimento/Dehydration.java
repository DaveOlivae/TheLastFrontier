package game.events.eventoDoencaFerimento;

import game.graphics.GamePanel;

public class Dehydration extends SicknessInjuryEvent {
    public Dehydration() {
        super("Desidratação",
                "Você está desidratado, beba água imediatamente.",
                "Desidratação",
                "Energia",
                "Água");
    }

    public void execute(GamePanel gp) {

    }
}
