package game.entity.npcs;

import game.entity.Entity;
import game.graphics.GamePanel;

public class NPC_Girl extends Entity {
    public NPC_Girl(GamePanel gp) {
        super("Girl", gp, 0, 0, 64, 64, 1, "down");

        loadImage("/npc/npc1.png");
        setDialogue();
    }

    public void setDialogue() {
        setDialogue("You can press 'q' to chek your inventory!");
        setDialogue("You don't remember? you seem to have been \nwandering around for days");
        setDialogue("Nobody survives the waste land, lad, you probably \nbeen robbed or something");
        setDialogue("The closest city is Ellis, maybe you'll find some \nanswers there");
    }

    public void speak() {
        super.speak();

    }
}
