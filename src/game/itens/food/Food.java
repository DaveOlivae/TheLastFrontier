package game.itens.food;

import game.entity.Player;
import game.itens.Consumable;

public abstract class Food extends Consumable {
    private int hungerPoints;  // pontos de fome restaurados

    public Food(String name, double weight, int hungerPoints) {
        super("food", name, weight);
        this.hungerPoints = hungerPoints;

        setStackable(true);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        setDescription("Name: " + getName() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Durability: " + getDurability() + "\n" +
                "Restores: " + getHungerPoints() + "\n");
    }

    @Override
    public void use(Player player) {
        player.eat(hungerPoints);
    }

    public int getHungerPoints() {
        return hungerPoints;
    }

}
