package jogo.itens;

import jogo.personagens.Personagem;

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

    public void getAttributes() {
        System.out.printf("\tNome: %s%n", getNome());
        System.out.printf("\tPeso: %d%n", getPeso());
        System.out.printf("\tDurabilidade: %d%n", getDurabilidade());

        if (this.equipavel) {
            System.out.println("\tEquipável?: Sim");
        } else {
            System.out.println("\tEquipável?: Não");
        }
    }

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
