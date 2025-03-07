package itens.remedios;

import itens.Item;

public class Remedio extends Item {
    private String tipo;
    private String efeito;

    public Remedio(String nome, int peso, int durabilidade, String tipo, String efeito) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.efeito = efeito;
    }
}
