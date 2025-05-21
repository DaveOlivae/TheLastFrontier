package game.ambientes;

import game.LidarComEventos;
import game.entity.Player;
import game.eventos.Evento;
import game.graphics.TileManager;
import game.itens.*;

import java.util.*;

public abstract class Ambiente implements LidarComEventos {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;  // a dificuldade de exploracao vai de 1 a 10
    private List<String> climasPossiveis;  // aqui eu to fazendo um lista pra guardar todos os climas
    private String climaAtual;  // esse eh o clima do turno
    private HashMap<Item, Integer> recursos;
    private List<Evento> eventosAtivos;

    private List<Item> itens;
    private TileManager tileM;

    public Ambiente(String nome, String descricao, int dificuldadeExploracao, TileManager tileM) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.climasPossiveis = new ArrayList<>();
        this.recursos = new HashMap<>();
        this.eventosAtivos = new ArrayList<>();

        this.itens = new ArrayList<>();
        this.tileM = tileM;

        adicionarItens();
        adicionarRecursos();
        atualizarClimas();
    }

    /* criacao e manutencao do ambiente */

    public abstract void adicionarItens();

    public abstract void adicionarRecursos();

    public abstract void atualizarClimas();

    public void carregarAmbiente(String path) {
        tileM.loadMap(path);
    }

    public void adicionarItem(Item item, int envX, int envY) {
        item.envX = envX;
        item.envY = envY;
        itens.add(item);
    }

    public void adicionarRecurso(Item recurso, Integer dificuldadeEncontro) {
        this.recursos.put(recurso, dificuldadeEncontro);
    }

    public void adicionarClima(String clima) {
        this.climasPossiveis.add(clima);
    }

    public void modificarClima() {
        Random random = new Random();
        this.climaAtual = climasPossiveis.get(random.nextInt(climasPossiveis.size()));
    }

    /* metodos relacionados a eventos */
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

    /* exploracao e encontrar itens */

    public void encontrarItens(Player jogador, Scanner input, HashMap<Item, Integer> lista) {

    }

    public void encontrarAbrigo(Player jogador) {

    }

    // gets e sets

    public List<Item> getItens() {
        return this.itens;
    }

    public void setDificuldadeExploracao(int dificuldadeExploracao) {
        this.dificuldadeExploracao = dificuldadeExploracao;
    }

    public int getDificuldadeExploracao() {
        return this.dificuldadeExploracao;
    }

    public String getClimaAtual() {
        return this.climaAtual;
    }

    public String getName() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
