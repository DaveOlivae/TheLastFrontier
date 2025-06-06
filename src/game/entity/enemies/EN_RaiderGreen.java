package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.Water;
import game.itens.food.Meat;
import game.itens.weapons.Ammo;
import game.itens.weapons.Knife;
import game.itens.weapons.Luger;

public class EN_RaiderGreen extends Enemy{
    public EN_RaiderGreen(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Knife());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/littleguy9.png");

        setLoot(new Ammo("pistol", 4));
        setLoot(new Meat());
        setLoot(new Luger());
        setLoot(new Water());
    }
}
