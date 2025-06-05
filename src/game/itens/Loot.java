package game.itens;

import java.util.List;
import java.util.ArrayList;

public class Loot extends Item{

    private List<Item> loot;

    public Loot() {
        super("loot", "loot", 1, 1, false);
        this.loot = new ArrayList<>();

        setImage("/itens/loot.png");
    }

    @Override
    public void updateDescription() {
        setDescription("null");
    }

    public void addLoot(Item item) {
        loot.add(item);
    }

    public List<Item> getLoot() {
        return loot;
    }
}
