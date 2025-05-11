package game.itens.remedios;

import game.itens.Item;

public abstract class Remedio extends Item {
    private String tipo;
    private String efeito;

    public Remedio(String nome, int peso, int durabilidade, String tipo, String efeito) {
        super(nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.efeito = efeito;
    }

    public void getAttributes() {
        super.getAttributes();
        System.out.printf("\tTipo: %s%n", this.getTipo());
        System.out.printf("\tEfeito: %s%n", this.getEfeito());
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getEfeito() {
        return this.efeito;
    }
}
