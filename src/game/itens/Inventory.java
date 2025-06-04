package game.itens;

import game.itens.weapons.Ammo;
import game.itens.weapons.Firearm;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final int maxSlots = 20;

    private double maxWeight;
    private double weight;
    private int slots;

    private List<Item> itens;

    public Inventory(double maxWeight) {
        this.maxWeight = maxWeight;
        this.itens = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if ((item.getWeight() + weight) <= maxWeight) {
            if (item.isStackable()) {

                if (searchItem(item) != null) {
                    Item element = searchItem(item);

                    element.setAmount(element.getAmount() + 1);

                } else {
                    if (++slots <= maxSlots) {
                        itens.add(item);
                    }
                }

                weight += item.getWeight();
            } else {
                if (++slots <= maxSlots) {
                    itens.add(item);
                    weight += item.getWeight();
                }
            }

            return true;
        }

        return false;
    }

    public Item searchItem(Item item) {
        String name = item.getName();

        for (Item element : itens) {
            if (name.equals(element.getName())) {
                return element;
            }
        }
        return null;
    }

    public void reloadFirearm(Firearm gun) {
        Ammo ammo;
        String type = gun.getFirearmType();
        if (!(getAmmo(type) == null)) {
            ammo = getAmmo(type);

            int quantity = ammo.getQuantity();

            if (quantity > gun.getCapacity()) {
                quantity -= gun.getCapacity();

                gun.loadGun(gun.getCapacity());
            } else if (quantity < gun.getCapacity() && quantity > 0) {
                gun.loadGun(quantity);
                quantity = 0;
            }

            ammo.setQuantity(quantity);
        }
    }

    public Ammo getAmmo(String ammoType) {
        for (Item item : itens) {
            if (item instanceof Ammo) {
                Ammo ammo = (Ammo) item;
                if (ammo.getName().equals(ammoType)) {
                    return ammo;
                }
            }
        }
        return null;
    }

    public Item getItem(int index) {
        return itens.get(index);
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getWeight() {
        return weight;
    }
}
