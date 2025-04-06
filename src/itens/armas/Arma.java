package itens.armas;

import itens.Item;
import personagens.Personagem;

public class Arma extends Item {
    private String tipo;
    private int dano;
    private int alcance;

    public Arma(String nome, int peso, int durabilidade, String tipo, int dano, int alcance) {
        super(nome, peso, durabilidade, true);
        this.tipo = tipo;
        this.dano = dano;
        this.alcance = alcance;
    }

    @Override
    public void usar(Personagem jogador) {

    }

    @Override
    public void getAttributes() {

    }
}
