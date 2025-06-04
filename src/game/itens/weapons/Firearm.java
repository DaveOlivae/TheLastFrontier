package game.itens.weapons;

public class Firearm extends Weapon {
    private int capacity;
    private int load;
    private String firearmType;

    public Firearm(String name, float weight, int durability, int capacity, int damage, int range, int load, String firearmType) {
        super(name, weight, durability, "firearm", damage, range);

        this.capacity = capacity;
        this.load = load;
        this.firearmType = firearmType;

        updateDescription();
    }

    public void updateDescription() {
        setDescription("Name: " + getName() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Durability: " + getDurability() + "\n" +
                "Damage: " + getDamage() + "\n" +
                "Range: " + getRange() + "\n" +
                "Ammo: " + "\n" +
                getLoad() + "/");
    }

    public String getFirearmType() {
        return firearmType;
    }

    public void loadGun(int points) {
        load = points;
    }

    public void shotGun() {
        load--;
    }

    public int getLoad() {
        return load;
    }

    public int getCapacity() {
        return capacity;
    }
}
