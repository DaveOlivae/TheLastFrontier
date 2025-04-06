package itens;

import personagens.Personagem;

public abstract class Item {
    private String nome;
    private int peso;
    private int durabilidade;
    private boolean equipavel;

    public Item(String nome, int peso, int durabilidade, boolean equipavel) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
        this.equipavel = equipavel;
    }

    public abstract void usar(Personagem jogador);

    public abstract void getAttributes();

    public String getNome() {
        return this.nome;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getDurabilidade() {
        return this.durabilidade;
    }

    public int getEficiencia() {
        return 0;
    }
}
