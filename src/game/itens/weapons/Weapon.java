package game.itens.weapons;

import game.itens.Item;

public abstract class Weapon extends Item {
    private String type;
    private int damage;
    private int range;

    public Weapon(String name, float weight, int durability, String type, int damage, int range) {
        super("weapon", name, weight, durability, true);
        this.type = type;
        this.damage = damage;
        this.range = range;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        setDescription("Name: " + getName() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Durability: " + getDurability() + "\n" +
                "Damage: " + getDamage() + "\n" +
                "Range: " + getRange() + "\n");
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
