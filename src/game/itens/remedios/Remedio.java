package game.itens.remedios;

import game.itens.Item;

public abstract class Remedio extends Item {
    private String tipo;
    private String efeito;

    public Remedio(String nome, int peso, int durabilidade, String tipo, String efeito) {
        super("consumable", nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.efeito = efeito;
    }
}
