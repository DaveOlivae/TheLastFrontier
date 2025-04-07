package jogo.itens;

import jogo.personagens.Personagem;

import java.util.ArrayList;
import java.util.List;

// TODO acho que da pra melhorar esse inventario, com quantidade de jogo.itens por exemplo

public class Inventario {
    private List<Item> itens;
    private int pesoTotal;
    private int espacoDisponivel;
    private int peso;
    private int espaco;

    public Inventario(int pesoTotal, int espacoDisponivel) {
        this.pesoTotal = pesoTotal;
        this.espacoDisponivel = espacoDisponivel;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        this.peso += item.getPeso();
        this.espaco++;  // a ideia eh que cada item ocupa um espaço mas talvez alguns jogo.itens possam ocupar mais de um
    }

    // esse metodo serve para que o usuario possa selecionar um item
    // as informacoes desse item serao imprimidas
    public void inspecionarItem(int i) {
        getAtributosItem(i);
    }

    public void removerItem(int i) {
        this.itens.remove(i);
    }

    // mudei a assinatura da funcao
    public void usarItem(int i, Personagem jogador) {
        this.itens.get(i).usar(jogador);
    }

    public void equiparItemInv(int i, Personagem jogador) {
        jogador.equiparItem(this.itens.get(i));
    }

    public boolean emptyInventory() {
        if (itens.isEmpty()) {
            System.out.println("Seu inventário está vazio");
        }
        return itens.isEmpty();
    }

    public void mostrarItens() {
        System.out.println("Inventário: ");
        for (int i = 1; i <= itens.size(); i++) {
            System.out.println(i + "- " + itens.get(i - 1).getNome());
        }
    }

    public void getAtributosItem(int i) {
        itens.get(i - 1).getAttributes();
    }

    public int getPesoTotal() {
        return this.pesoTotal;
    }

    public int getEspacoDisponivel() {
        return this.espacoDisponivel;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getEspaco() {
        return this.espaco;
    }
}
