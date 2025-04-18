package jogo.itens.alimentos;

import jogo.itens.Item;
import jogo.personagens.Personagem;

public class Alimento extends Item {
    private int valorNutricional;  // pontos de fome restaurados
    private String tipo;  // carne, fruta, etc
    private int validade;  // dias ate estragar

    public Alimento(String nome, int peso, int durabilidade, int valorNutricional, String tipo, int validade) {
        super(nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.valorNutricional = valorNutricional;
        this.validade = validade;
    }

    public void usar(Personagem jogador) {

    }

    public void getAttributes() {
        System.out.printf("- Nome: %s%n" +
                "- Tipo: %s%n" +
                "- Peso: %d%n" +
                "- Valor Nutricional: %d%n", super.getNome(), getTipo(), super.getPeso(), getValorNutricional());
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getValorNutricional() {
        return this.valorNutricional;
    }

    public int getValidade() {
        return this.validade;
    }
}
