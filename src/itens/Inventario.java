package itens;

import java.util.ArrayList;
import java.util.List;

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
        this.espaco++;  // a ideia eh que cada item ocupa um espaço mas talvez alguns itens possam ocupar mais de um
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
