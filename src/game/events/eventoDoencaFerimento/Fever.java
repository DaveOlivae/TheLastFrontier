package game.events.eventoDoencaFerimento;

import game.graphics.GamePanel;

public class Fever extends SicknessInjuryEvent {
    public Fever(double probability) {
        super("fever",
                "You've got a fever\nYou're feeling too tired\nBetter find medication.",
                probability);
    }

    public void execute(GamePanel gp) {
        gp.gameState = gp.dialogueState;
        gp.getUi().setCurrentDialogue(getMessage());
    }

    public void update(GamePanel gp) {
        if (gp.getClock().getTime() % 200 == 0) {
            gp.getPlayer().setEnergy(gp.getPlayer().getEnergy() - 1);
        }
    }
}
