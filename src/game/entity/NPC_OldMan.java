package game.entity;

import game.graphics.GamePanel;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp, int envX, int envY) {
        super(gp, 0, 0, 64, 64, envX, envY, 1, "down");

        setDirection("down");
        setSpeed(1);

        getPlayerImage("/npc/npc1.png");
    }
}
