package game.events.eventoDoencaFerimento;

import game.graphics.GamePanel;

public class Hypotermia extends SicknessInjuryEvent {
    public Hypotermia(double probability) {
        super("hypotermia",
                "Because of the cold you've\nbecame hypotermic.\nFind a source of heat soon.",
                probability);
    }

    public void execute(GamePanel gp) {
        gp.gameState = gp.dialogueState;
        gp.getUi().setCurrentDialogue(getMessage());
    }

    public void update(GamePanel gp) {
        if (gp.getClock().getTime() % 200 == 0) {
            gp.getPlayer().damage(1);
        }
    }
}
