package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.weapons.Revolver;

public class EN_Raider extends Enemy {
    public EN_Raider(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Revolver());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/raider1.png");
    }
}
