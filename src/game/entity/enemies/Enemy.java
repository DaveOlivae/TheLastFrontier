package game.entity.enemies;

import game.entity.Entity;
import game.graphics.GamePanel;
import game.itens.Item;
import game.itens.Loot;

public abstract class Enemy extends Entity {
    private Item equippedItem;
    private Loot loot;

    public Enemy(String name, GamePanel gp, int x, int y, int width, int height, int speed, String direction, Item equippedItem) {
        super(name, gp, x, y, width, height, speed, direction);

        this.loot = new Loot();
        this.equippedItem = equippedItem;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setLoot(Item item) {
        loot.addLoot(item);
    }

    public Loot getLoot() {
        return loot;
    }
}
