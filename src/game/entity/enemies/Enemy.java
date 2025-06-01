package game.entity.enemies;

import game.entity.Entity;
import game.graphics.GamePanel;
import game.itens.Item;

public class Enemy extends Entity {
    private Item equippedItem;

    public Enemy(String name, GamePanel gp, int x, int y, int width, int height, int speed, String direction, Item equippedItem) {
        super(name, gp, x, y, width, height, speed, direction);

        this.equippedItem = equippedItem;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }
}
