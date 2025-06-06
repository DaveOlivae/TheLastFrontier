package game.itens;

import game.entity.Player;

public abstract class Consumable extends Item{
    public Consumable(String type, String name, double weight) {
        super(type, name, weight, 1, false);

        setStackable(true);
    }

    public abstract void use(Player player);
}
