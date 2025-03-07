package itens.armas;

import itens.Item;

public class Arma extends Item {
    private String tipo;
    private int dano;
    private int alcance;

    public Arma(String nome, int peso, int durabilidade, String tipo, int dano, int alcance) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.dano = dano;
        this.alcance = alcance;
    }
}
