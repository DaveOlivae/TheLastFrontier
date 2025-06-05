package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.food.Soup;
import game.itens.weapons.Ammo;
import game.itens.weapons.M92;
import game.itens.weapons.Revolver;

public class EN_RaiderOrange extends Enemy{
    public EN_RaiderOrange(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Revolver());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/littleguy6.png");

        setLoot(new Ammo("pistol", 4));
        setLoot(new M92());
        setLoot(new Soup());
    }
}
