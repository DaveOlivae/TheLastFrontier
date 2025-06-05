package game.itens.food;

import game.itens.Item;

public abstract class Food extends Item {
    private int hungerPoints;  // pontos de fome restaurados

    public Food(String name, double weight, int durability, int hungerPoints) {
        super("food", name, weight, durability, false);
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

    public int getHungerPoints() {
        return hungerPoints;
    }

}
