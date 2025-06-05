package game.entity.enemies;

import game.graphics.GamePanel;
import game.itens.food.Pomegranate;
import game.itens.remedios.Bandages;
import game.itens.weapons.Ammo;
import game.itens.weapons.Revolver;

public class EN_RaiderBlue extends Enemy{
    public EN_RaiderBlue(GamePanel gp) {
        super("Raider", gp, 0, 0, 64, 64, 1, "down", new Revolver());

        // attributes
        setMaxLife(100);
        setLife(getMaxLife());

        loadImage("/enemies/littleguy5.png");

        setLoot(new Ammo("pistol", 4));
        setLoot(new Bandages());
        setLoot(new Pomegranate());
    }
}
