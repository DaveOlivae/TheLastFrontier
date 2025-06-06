package game.entity.npcs;

import game.entity.Entity;
import game.graphics.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super("Old man", gp, 0, 0, 64, 64, 1, "down");

        loadImage("/npc/npc1.png");
        setDialogue();
    }

    public void setDialogue() {
        setDialogue("What? who are you? how did you end up here?");
        setDialogue("You don't remember? you seem to have been \nwandering around for days");
        setDialogue("Nobody survives the waste land, lad, you probably \nbeen robbed or something");
        setDialogue("The closest city is Ellis, just follow the roads\nwest");
        setDialogue("There are 2 other cities around , Pencey and\n St. Francis. Pencey is up north and\nSt. Francis is NorthWest");
        setDialogue("Good luck");
    }

    public void speak() {
        super.speak();
    }
}
