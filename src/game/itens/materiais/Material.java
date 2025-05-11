package game.itens.materiais;

import game.itens.Item;
import game.entity.Player;

public abstract class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material(String nome, int peso, int durabilidade, String tipo, int resistencia) {
        super(nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    @Override
    public void usar(Player jogador) {

    }

    public void getAttributes() {
        super.getAttributes();
        System.out.printf("\tTipo: %s%n", this.tipo);
        System.out.printf("\tResistencia: %d%n", this.resistencia);
    }

    public String getTipo() {
        return this.tipo;
    }
}
