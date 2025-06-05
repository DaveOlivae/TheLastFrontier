package game.entity;

import game.graphics.GamePanel;
import game.input.KeyHandler;
import game.itens.Canteen;
import game.itens.remedios.FirstAidKit;
import game.itens.weapons.Ammo;
import game.itens.weapons.Knife;

public class Medic extends Player {
    public Medic(String name, GamePanel gp, KeyHandler keyH) {
        super(name,
                100,
                100,
                100,
                100,
                100,
                30,
                gp,
                keyH);

        loadImage("/sprites/medic.png");
    }

    public void starterKit() {
        addItem(new Knife());
        addItem(new Canteen());
        addItem(new Ammo("pistol", 12));
        addItem(new FirstAidKit());

        equipItem(itens().getFirst());
    }
}
