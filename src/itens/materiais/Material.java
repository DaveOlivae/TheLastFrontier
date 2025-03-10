package itens.materiais;

import itens.Item;

public class Material extends Item {
    private String tipo;
    private int resistencia;

    public Material(String nome, int peso, int durabilidade, String tipo, int resistencia) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    public void getAttributes() {
        System.out.printf("- Nome: %s%n- Tipo: %s%n- Peso: %s%n", getNome(), getTipo(), getPeso());
    }

    public String getTipo() {
        return this.tipo;
    }
}
