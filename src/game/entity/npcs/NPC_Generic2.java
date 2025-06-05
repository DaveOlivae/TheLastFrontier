package game.entity.npcs;

import game.entity.Entity;
import game.graphics.GamePanel;

public class NPC_Generic2 extends Entity {
    public NPC_Generic2(GamePanel gp) {
        super("Girl", gp, 0, 0, 64, 64, 1, "down");

        loadImage("/npc/npc1.png");
        setDialogue();
    }

    public void setDialogue() {
        setDialogue("I'm leaving soon");
        setDialogue("You're new here?");
    }

    public void speak() {
        super.speak();

    }
}
