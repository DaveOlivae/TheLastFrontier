package personagens;

import itens.*;

public abstract class Personagem {
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

    public void attFomeSede() {
        this.fome += 3;
        this.sede += 4;  // sede maior que a fome
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
        return !this.inventario.emptyInventory();
    }

    public void showAttributes() {
        System.out.printf("- Vida: %d%n" +
                        "- Fome: %d%n" +
                        "- Sede: %d%n" +
                        "- Energia: %d%n" +
                        "- Sanidade: %d%n" +
                        "- Peso Total: %d/%d%n" +
                        "- Espaço Total: %d/%d%n",
                getVida(), getFome(), getSede(), getEnergia(), getSanidade(),
                inventario.getPeso(), inventario.getPesoTotal(),
                inventario.getEspaco(), inventario.getPesoTotal());
    }

    public void restaurarFome(int pontos) {
        this.fome += pontos;
    }

    public void restaurarSede(int pontos) {
        this.sede += pontos;
    }

    public void usarItem(int i, Personagem jogador) {
        inventario.usarItem(i, jogador);
    }

    public String getName() {
        return this.nome;
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

    public int getInvPeso() {
        return this.inventario.getPeso();
    }

    public int getInvEspaco() {
        return this.inventario.getEspaco();
    }

    public int getInvPesoTot() {
        return this.inventario.getPesoTotal();
    }

    public int getInvEspDisp() {
        return this.inventario.getEspacoDisponivel();
    }
}
