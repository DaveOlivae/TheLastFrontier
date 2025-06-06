package game.entity.npcs;

import game.entity.Entity;
import game.graphics.GamePanel;

public class NPC_Generic3 extends Entity{
    public NPC_Generic3(GamePanel gp, int num) {
        super("Girl2", gp, 0, 0, 64, 64, 1, "down");

        loadImage("/npc/littleguy10.png");
        setDialogues(num);
    }

    public void setDialogues(int num) {

        switch (num) {
            case 0:
                setDialogue("This city is so nice");
                setDialogue("I like it here a lot");
                break;
            case 1:
                setDialogue("The waters in the river and ponds are\n drinkable");
                setDialogue("You'll be able to fill you canteen.");
                setDialogue("The river? go west and then go north\nYou'll find it.");
        }
    }

    public void speak() {
        super.speak();

    }
}
