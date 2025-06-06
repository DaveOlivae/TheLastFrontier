package game.entity;

import game.graphics.GamePanel;
import game.input.KeyHandler;
import game.itens.Canteen;
import game.itens.food.Meat;
import game.itens.remedios.Bandages;
import game.itens.weapons.Ammo;
import game.itens.weapons.Knife;

public class Survivor extends Player {
    public Survivor(String name, GamePanel gp, KeyHandler keyH) {
        super(name,
                100,
                100,
                100,
                100,
                100,
                30,
                gp,
                keyH);

        loadImage("/sprites/survivor.png");
    }

    public void starterKit() {
        addItem(new Knife());
        addItem(new Canteen());
        addItem(new Ammo("pistol", 16));
        addItem(new Meat());
        addItem(new Bandages());

        equipItem(itens().getFirst());
    }
}
