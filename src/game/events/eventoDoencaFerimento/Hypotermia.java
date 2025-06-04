package game.events.eventoDoencaFerimento;

import game.graphics.GamePanel;

public class Hypotermia extends SicknessInjuryEvent {
    public Hypotermia() {
        super("Hipotermia",
                "Você está com hipotermia — seu corpo está perdendo " +
                "calor mais rápido do que consegue produzir. Você perderá vida até se aquecer.",
                "Hipotermia",
                "Vida",
                "Abrigo");
    }

    public void execute(GamePanel gp) {

    }
}
