package game.itens.tools;

import game.itens.Item;

public class Ferramenta extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramenta(String nome, int peso, int durabilidade, String tipo, int eficiencia) {
        super("tool", nome, peso, durabilidade, true);
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }
}
