package game.itens.food;

import game.entity.Player;
import game.itens.Item;

public abstract class Food extends Item {
    private int hungerPoints;  // pontos de fome restaurados
    private int expiration;  // dias ate estragar

    public Food(String name, double weight, int durability, int hungerPoints, int expiration) {
        super("food", name, weight, durability, false);
        this.hungerPoints = hungerPoints;
        this.expiration = expiration;

        setDescription("Name: " + getName() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Durability: " + getDurability() + "\n" +
                "Restores: " + getHungerPoints() + "\n" +
                "Expiration: " + getExpiration() + "\n");
    }

    public void eat(Player player) {
        player.eat(hungerPoints);
    }

    public int getHungerPoints() {
        return hungerPoints;
    }

    public int getExpiration() {
        return expiration;
    }
}
