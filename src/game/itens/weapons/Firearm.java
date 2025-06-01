package game.itens.weapons;

public class Firearm extends Weapon {
    public int capacity;
    public int ammo;
    public int load;

    public Firearm(String name, float weight, int durability, int capacity, int damage, int range, int ammo, int load) {
        super(name, weight, durability, "firearm", damage, range);

        this.capacity = capacity;
        this.ammo = ammo;
        this.load = load;

        setDescription("Name: " + getName() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Durability: " + getDurability() + "\n" +
                "Damage: " + getDamage() + "\n" +
                "Range: " + getRange() + "\n" +
                "Ammo: " + "\n" +
                getLoad() + "/" + getAmmo() + "\n");
    }

    public int getLoad() {
        return load;
    }

    public int getAmmo() {
        return ammo;
    }
}
