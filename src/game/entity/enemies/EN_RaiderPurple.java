package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.Water;
import game.itens.food.Coffee;
import game.itens.remedios.Bandages;
import game.itens.weapons.Ammo;
import game.itens.weapons.Knife;

public class EN_RaiderPurple extends Enemy {
    public EN_RaiderPurple(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Knife());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/raider1.png");

        setLoot(new Ammo("pistol", 10));
        setLoot(new Coffee());
        setLoot(new Bandages());
        setLoot(new Water());
    }
}
