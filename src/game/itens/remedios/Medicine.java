package game.itens.remedios;

import game.itens.Consumable;

public abstract class Medicine extends Consumable {

    public Medicine(String nome, int peso) {
        super("consumable", nome, peso);
    }
}
