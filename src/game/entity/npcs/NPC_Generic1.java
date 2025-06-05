package game.entity.npcs;

import game.entity.Entity;
import game.graphics.GamePanel;

public class NPC_Generic1 extends Entity {
    public NPC_Generic1(GamePanel gp) {
        super("Man", gp, 0, 0, 64, 64, 1, "down");

        loadImage("/npc/npc1.png");
        setDialogue();
    }

    public void setDialogue() {
        setDialogue("I'll never leave this city");
        setDialogue("I've lived on the wasteland");
        setDialogue("Go away i don't want to talk");
    }

    public void speak() {
        super.speak();

    }
}
