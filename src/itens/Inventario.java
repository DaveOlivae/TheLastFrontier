package itens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    private List<Item> itens;
    private int pesoTotal;
    private int espacoDisponivel;

    public Inventario(int pesoTotal, int espacoDisponivel) {
        this.pesoTotal = pesoTotal;
        this.espacoDisponivel = espacoDisponivel;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    // esse metodo serve para que o usuario possa selecionar um item
    // as informacoes desse item serao imprimidas
    public void inspecionarItem(int i) {
        if (!itens.isEmpty()) {
            getAtributosItem(i);
        } else {
            System.out.println("Seu inventário está vazio!");
        }
    }

    public void getAtributosItem(int i) {
        itens.get(i - 1).getAttributes();
    }

    public void removerItem(int i) {
        if (itens.isEmpty()) {
            System.out.println("O seu inventário está vazio");
        } else {
            this.itens.remove(i);
        }
    }

    public void usarItem(String nomeItem) {

    }

    public boolean emptyInventory() {
        return itens.isEmpty();
    }

    public void mostrarItens() {
        if (itens.isEmpty()) {
            System.out.println("O seu inventário está vazio");
        } else {
            System.out.println("Inventário: ");
            for (int i = 1; i <= itens.size(); i++) {
                System.out.println(i + "- " + itens.get(i - 1).getNome());
            }
        }
    }
}
