package game.itens.materiais;

import game.itens.Item;
import game.entity.Player;

public abstract class Material extends Item {

    public Material(String name, double weight) {
        super("material", name, weight, 1, false);

        setStackable(true);
    }
}
