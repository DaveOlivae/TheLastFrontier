package game.ambientes;

import game.LidarComEventos;
import game.entity.Player;
import game.eventos.Evento;
import game.itens.*;

import java.util.*;

public abstract class Ambiente implements LidarComEventos {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;  // a dificuldade de exploracao vai de 1 a 10
    private List<String> climasPossiveis;  // aqui eu to fazendo um lista pra guardar todos os climas
    private String climaAtual;  // esse eh o clima do turno
    private HashMap<Item, Integer> itens;
    private HashMap<Item, Integer> recursos;
    private List<Evento> eventosAtivos;

    public Ambiente(String nome, String descricao, int dificuldadeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.climasPossiveis = new ArrayList<>();
        this.recursos = new HashMap<>();
        this.itens = new HashMap<>();
        this.eventosAtivos = new ArrayList<>();

        adicionarItens();
        adicionarRecursos();
        atualizarClimas();
    }

    /* criacao e manutencao do ambiente */
    public abstract void adicionarItens();

    public abstract void adicionarRecursos();

    public abstract void atualizarClimas();

    public void adicionarRecurso(Item recurso, Integer dificuldadeEncontro) {
        this.recursos.put(recurso, dificuldadeEncontro);
    }

    public void adicionarItem(Item item, Integer dificuldadeEncontro) {
        this.itens.put(item, dificuldadeEncontro);
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

    // essa eh a funcao que vai lidar com as acoes do jogador no ambiente
    public void explorar(Player jogador, Scanner input) {
        Random random = new Random();
        System.out.printf("Você adentra %s%n", getDescricao());
        System.out.println("O percurso é cansativo, você perde 30 de energia.");

        // diminui a energia
        jogador.attEnergia(30);

        int teste = jogador.getExploracao() + random.nextInt(10);
        int dificuldade = getDificuldadeExploracao(); // vai printar um ngc na tela

        if (teste >= dificuldade) {
            System.out.println("Você está adentrando o ambiente com sucesso");
            System.out.println("O que você deseja fazer durante a exploração?");
            System.out.printf("\t1 - Encontrar Itens%n" +
                    "\t2 - Coletar Recursos%n" +
                    "\t3 - Procurar Abrigo%n" +
                    "Sua resposta: ");

            int resposta = input.nextInt();
            input.nextLine();

            if (resposta == 1) {
                encontrarItens(jogador, input, this.itens);
            } else if (resposta == 2) {
                encontrarItens(jogador, input, this.recursos);
            } else if (resposta == 3) {
                encontrarAbrigo(jogador);
            } else {
                System.out.printf("Resposta inválida.%n");
            }
        } else if (teste >= dificuldade - 2) {
            System.out.println("Você cai e se machuca, melhor nao continuar a explorar.");
            System.out.println("Você perdeu 10 pontos de vida");
            jogador.diminuirVida(10);
        } else {
            System.out.println("Você nao conseguiu explorar e perde 20 pontos de vida");
            jogador.diminuirVida(20);
        }
    }

    // esse metodo eh responsavel por lidar com o encontro de itens e adiciona-los no inventario, se possivel
    public void encontrarItens(Player jogador, Scanner input, HashMap<Item, Integer> lista) {
        // da pra melhorar algumas coisas por aqui, mas vou manter assim so por teste
        Random random = new Random();

        int rastreamento = jogador.getRastreamento();

        // a eq pra a jogada de dado, o rastreamento + um numero aleatorio, cujo bound eh a qtd que sobra ate 20
        int diceRoll = rastreamento + (random.nextInt(20 - rastreamento) + 1);

        List<Item> itensEncontrados = new ArrayList<>();

        for (Map.Entry<Item, Integer> entrada : lista.entrySet()) {
            if (diceRoll >= entrada.getValue()) {
                itensEncontrados.add(entrada.getKey());
            }
        }

        System.out.println("Você encontrou: ");
        for (int i = 0; i < itensEncontrados.size(); i++) {
            System.out.printf("\t%d - %s%n", i, itensEncontrados.get(i).getNome());
        }

        System.out.print("> Quais itens você deseja manter? (separar por vírgulas sem espaço, all = mantém todos): ");
        String resposta = input.nextLine().trim();

        if (resposta.equals("all")) {
            for (Item item : itensEncontrados) {
                jogador.addItemInventario(item);
                lista.remove(item);
            }
        } else {
            String[] partes = resposta.split(",");

            List<Integer> ints = new ArrayList<>();

            for (String parte : partes) {
                ints.add(Integer.parseInt(parte));
            }

            for (Integer index : ints) {
                Item item = itensEncontrados.get(index);
                jogador.addItemInventario(item);
                lista.remove(item);
            }
        }
    }

    public void encontrarAbrigo(Player jogador) {
        Random random = new Random();

        int exploracao = jogador.getExploracao();
        int diceRoll = exploracao + random.nextInt((20 - exploracao) + 1);

        if (diceRoll >= this.dificuldadeExploracao) {
            System.out.println("Você encontrou abrigo! sua energia foi restaurada.");
            jogador.attEnergia(100 - jogador.getEnergia());
        } else {
            System.out.println("Você não consegui encontrar abrigo.");
        }
    }

    public void informacao() {
        System.out.printf("==> Você chegou num ambiente de %s.%n", this.nome);
        System.out.printf("\t'%s'%n", this.descricao);
    }

    // gets e sets


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
