package game.itens.materiais;

import game.itens.Item;
import game.entity.Player;

public abstract class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material(String nome, int peso, int durabilidade, String tipo, int resistencia) {
        super("material", nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }
}
