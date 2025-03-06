package itens;

import java.util.ArrayList;
import java.util.List;

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

    public void removerItem(String nomeItem) {

    }

    public void usarItem(String nomeItem) {

    }

    public void mostrarItens() {
        if (itens.isEmpty()) {
            System.out.println("O seu inventário está vazio");
        } else {
            System.out.println("Inventário: ");
            for (int i = 1; i <= itens.size(); i++) {
                System.out.println(i + "- " + itens.get(i - 1));
            }
        }
    }
}
