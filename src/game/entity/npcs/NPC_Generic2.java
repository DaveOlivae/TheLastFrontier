package game.entity.npcs;

import game.entity.Entity;
import game.graphics.GamePanel;

public class NPC_Generic2 extends Entity {
    public NPC_Generic2(GamePanel gp, int num) {
        super("Girl", gp, 0, 0, 64, 64, 1, "down");

        loadImage("/npc/littleguy8.png");
        setDialogues(num);
    }

    public void setDialogues(int num) {

        switch (num) {
            case 0:
                setDialogue("I'm leaving soon");
                setDialogue("You're new here?");
                break;
            case 1:
                setDialogue("It is said that there's\nspecial resting places in the wastes\ncreated by adventurers");
                setDialogue("If you wander around.\nAway from the road\nMaybe you'll find 'em");
        }
    }

    public void speak() {
        super.speak();

    }
}
