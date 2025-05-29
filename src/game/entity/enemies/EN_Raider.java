package game.entity.enemies;

import game.entity.Entity;
import game.graphics.GamePanel;

public class EN_Raider extends Entity {
    public EN_Raider(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down");

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/raider1.png");
    }
}
