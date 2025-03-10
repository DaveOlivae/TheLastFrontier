package personagens;

import itens.*;

public class Personagem {
    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private Inventario inventario;

    public Personagem(String nome, int vida, int fome, int sede, int energia, int sanidade, int pesoTotal, int espacoDisponivel) {
        this.nome = nome;
        this.vida = vida;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
        this.sanidade = sanidade;
        this.inventario = new Inventario(pesoTotal, espacoDisponivel);
    }

    public void mostrarInventario() {
        this.inventario.mostrarItens();
    }

    public void inspecionarItem(int i) {
        this.inventario.inspecionarItem(i);
    }

    public void addItemInventario(Item item) {
        this.inventario.adicionarItem(item);
    }

    public void remvItemInventario(int i) {
        this.inventario.removerItem(i);
    }

    public boolean playerInvEmpty() {
        return this.inventario.emptyInventory();
    }

    public String getName() {
        return this.nome;
    }

    public void showAttributes() {
        System.out.printf("- Vida: %d%n- Fome: %d%n- Sede: %d%n- Energia: %d%n- Sanidade: %d%n",
                getVida(), getFome(), getSede(), getEnergia(), getSanidade());
    }

    public int getVida() {
        return this.vida;
    }

    public int getFome() {
        return this.fome;
    }

    public int getSede() {
        return this.sede;
    }

    public int getEnergia() {
        return this.energia;
    }

    public int getSanidade() {
        return this.sanidade;
    }
}
