package itens.alimentos;

import itens.Item;

public class Alimento extends Item {
    private int valorNutricional;  // pontos de fome restaurados
    private String tipo;  // carne, fruta, etc
    private int validade;  // dias ate estragar

    public Alimento(String nome, int peso, int durabilidade, int valorNutricional, String tipo, int validade) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.valorNutricional = valorNutricional;
        this.validade = validade;
    }
}
