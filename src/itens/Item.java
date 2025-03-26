package itens;

import personagens.Personagem;

public abstract class Item {
    private String nome;
    private int peso;
    private int durabilidade;

    public Item(String nome, int peso, int durabilidade) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
    }

    public void usar(Personagem jogador) {}

    public void getAttributes() {}

    public String getNome() {
        return this.nome;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getDurabilidade() {
        return this.durabilidade;
    }
}
