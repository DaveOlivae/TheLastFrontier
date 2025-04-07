package jogo.itens.materiais;

import jogo.itens.Item;
import jogo.personagens.Personagem;

public class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material(String nome, int peso, int durabilidade, String tipo, int resistencia) {
        super(nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    @Override
    public void usar(Personagem jogador) {

    }

    public void getAttributes() {
        System.out.printf("- Nome: %s%n- Tipo: %s%n- Peso: %s%n", getNome(), getTipo(), getPeso());
    }

    public String getTipo() {
        return this.tipo;
    }
}
