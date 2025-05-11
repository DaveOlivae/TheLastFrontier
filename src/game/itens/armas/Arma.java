package game.itens.armas;

import game.itens.Item;

public abstract class Arma extends Item {
    private String tipo;
    private int dano;
    private int alcance;

    public Arma(String nome, int peso, int durabilidade, String tipo, int dano, int alcance) {
        super(nome, peso, durabilidade, true);
        this.tipo = tipo;
        this.dano = dano;
        this.alcance = alcance;
    }

    public void getAttributes() {
        super.getAttributes();
        System.out.printf("\tTipo: %s%n", this.tipo);
        System.out.printf("\tDano: %d%n", this.dano);
        System.out.printf("\tAlcance: %d%n", this.alcance);
    }

    public int getDano() {
        return this.dano;
    }

    public int getAlcance() {
        return this.alcance;
    }

    public String getTipo() {
        return this.tipo;
    }
}
