package game.itens.weapons;

import game.itens.Item;

public class Ammo extends Item {
    private int quantity;

    public Ammo(String name, int staterQuantity) {
        super("ammo",
                name,
                0.01F,
                1,
                false);

        quantity = staterQuantity;

        if (name.equals("pistol")) {
            setImage("/itens/bullet.png");
            setDescription("Pistol ammo\nQuantity: " + getQuantity());
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
