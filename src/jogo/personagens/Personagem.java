package jogo.personagens;

import jogo.eventos.Evento;
import jogo.itens.*;

import jogo.LidarComEventos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO implementar energia

public abstract class Personagem implements LidarComEventos {
    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private int exploracao;
    private int rastreamento;
    private Inventario inventario;
    private Item itemEquipado;
    private List<Evento> eventosAtivos;  // essa lista vai guardar os jogo.eventos ativos relacionados ao personagem
    private boolean protegido; // fiz esse atributo pra caso o jogador encontre abrigo não precisar passar por
                                // evento aleatorio

    public Personagem(String nome, int vida, int fome, int sede, int energia, int sanidade, int pesoTotal,
                      int espacoDisponivel, int exploracao, int rastreamento) {
        this.nome = nome;
        this.vida = vida;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
        this.sanidade = sanidade;
        this.inventario = new Inventario(pesoTotal, espacoDisponivel);
        this.exploracao = exploracao;  // exploracao eh habilidade de explorar e vai de 1 a 20
        this.rastreamento = rastreamento; // tbm vai de 1 a 20
        this.protegido = false; // o jogador vai comecar nao estando protegido
        this.eventosAtivos = new ArrayList<>();
    }

    /* metodos relacionados aos jogo.eventos */

    @Override
    public void adicionarEvento(Evento evento) {
        this.eventosAtivos.add(evento);
    }

    @Override
    public boolean eventoNaLista(Class<? extends Evento> tipo) {
        for (Evento e : this.eventosAtivos) {
            if (tipo.isInstance(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removerEvento(Class<? extends Evento> tipo) {
    }

    /* metodos relacionados aos itens e inventario */

    public void acessarInventario(Scanner input) {
        this.inventario.acessarInventario(input, this);
    }

    public void mostrarInventario() {
        this.inventario.mostrarItens();
    }

    public boolean playerInvEmpty() {
        return this.inventario.emptyInventory();
    }

    public void addItemInventario(Item item) {
        this.inventario.adicionarItem(item);
    }

    public void remvItemInventario(int i) {
        this.inventario.removerItem(i);
    }

    public void inspecionarItem(int i) {
        this.inventario.inspecionarItem(i);
    }

    public void usarItem(int i, Personagem jogador) {
        inventario.usarItem(i, jogador);
    }

    public void equiparItem(Item item) {
        this.itemEquipado = item;
    }

    /* metodos relacionados aos atributos*/

    public void attFomeSede() {
        this.fome += 3;
        this.sede += 4;  // sede maior que a fome
    }

    public void attEnergia(int pontos) {
        if ((this.energia -= pontos) <= 0) {
            this.energia = 0;
        }
    }

    public void attVida(int pontos) {

    }

    public void restaurarFome(int pontos) {
        this.fome -= pontos;
    }

    public void restaurarSede(int pontos) {
        this.sede -= pontos;
    }

    public void diminuirVida(int valor) {
        this.vida -= valor;
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

    public void estaProtegido() {
        this.protegido = true;
    }

    // gets e sets

    public int getExploracao() {
        return this.exploracao;
    }

    public Item getItemEquipado() {
        return this.itemEquipado;
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

    public int getRastreamento() {
        return this.rastreamento;
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
