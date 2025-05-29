package game.itens;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final int maxSlots = 20;

    private float maxWeight;
    private float weight;
    private int slots;

    private List<Item> itens;

    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
        this.itens = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (((item.getWeight() + weight) <= maxWeight) && ++slots <= maxSlots) {
            itens.add(item);

            weight += item.getWeight();
            slots++;

            return true;
        }

        return false;
    }

    public Item getItem(int index) {
        return itens.get(index);
    }

    public List<Item> getItens() {
        return itens;
    }

}
