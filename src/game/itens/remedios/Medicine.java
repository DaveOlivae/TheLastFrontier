package game.itens.remedios;

import game.itens.Item;

public abstract class Medicine extends Item {

    public Medicine(String nome, int peso, int durabilidade) {
        super("consumable", nome, peso, durabilidade, false);

    }
}
