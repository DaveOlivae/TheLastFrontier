package game.entity;

import game.graphics.GamePanel;
import game.input.KeyHandler;
import game.itens.Canteen;
import game.itens.weapons.Ammo;
import game.itens.weapons.Knife;

public class Scientist extends Player {
    public Scientist(String name, GamePanel gp, KeyHandler keyH) {
        super(name,
                100,
                100,
                100,
                100,
                100,
                30,
                gp,
                keyH);

        loadImage("/sprites/scientist.png");
    }

    public void starterKit() {
        addItem(new Knife());
        addItem(new Canteen());
        addItem(new Ammo("pistol", 12));

        equipItem(itens().getFirst());
    }
}
