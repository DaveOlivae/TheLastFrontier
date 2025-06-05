package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.food.Bread;
import game.itens.food.Strawberry;
import game.itens.weapons.Ammo;
import game.itens.weapons.Revolver;

public class EN_RaiderPink extends Enemy{
    public EN_RaiderPink(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Revolver());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/littleguy3.png");

        setLoot(new Ammo("pistol", 4));
        setLoot(new Bread());
        setLoot(new Strawberry());
    }
}
