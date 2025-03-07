package itens.ferramentas;

import itens.Item;

public class Ferramenta extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramenta(String nome, int peso, int durabilidade, String tipo, int eficiencia) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }
}
