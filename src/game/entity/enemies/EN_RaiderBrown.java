package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.food.Coffee;
import game.itens.remedios.FirstAidKit;
import game.itens.weapons.Ammo;
import game.itens.weapons.Revolver;

public class EN_RaiderBrown extends Enemy{
    public EN_RaiderBrown(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Revolver());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/littleguy2.png");

        setLoot(new Ammo("pistol", 4));
        setLoot(new FirstAidKit());
        setLoot(new Coffee());
    }
}
