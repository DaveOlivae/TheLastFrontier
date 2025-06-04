package game.events.eventoDoencaFerimento;

import game.graphics.GamePanel;

public class Dehydration extends SicknessInjuryEvent {
    public Dehydration(double probability) {
        super("dehydration",
                "The heat is unbearable.\nYou're dehydrated\nDrink water!",
                probability);
    }

    public void execute(GamePanel gp) {
        gp.gameState = gp.dialogueState;
        gp.getUi().setCurrentDialogue(getMessage());
    }

    public void update(GamePanel gp) {
        if (gp.getClock().getTime() % 200 == 0) {
            gp.getPlayer().setThirst(gp.getPlayer().getThirst() - 1);
        }
    }
}
