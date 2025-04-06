package ambientes;

// TODO : Implementar dificuldade de exploração
// TODO : Implementar dificuldade de encontrar itens

import eventos.Evento;
import itens.*;
import personagens.*;

import java.util.*;

public abstract class Ambiente {
    // atributos
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;  // a dificuldade de exploracao vai de 1 a 10
    private List<String> climasPossiveis;  // aqui eu to fazendo um lista pra guardar todos os climas
    private String climaAtual;  // esse eh o clima do turno
    private List<Item> recursos;
    private Map<Evento, Double> eventosPossiveis;

    // construtor
    public Ambiente(String nome, String descricao, int dificuldadeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.climasPossiveis = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.eventosPossiveis = new HashMap<>();

        adicionarRecursos();
        atualizarClimas();
    }

    // metodos
    public abstract void adicionarRecursos();

    public abstract void atualizarClimas();

    public void adicionarEventos(Evento evento, double probabilidade) {
        this.eventosPossiveis.put(evento, probabilidade);
    }

    public void adicionarItem(Item item) {
        recursos.add(item);
    }

    public void adicionarClima(String clima) {
        climasPossiveis.add(clima);
    }

    /* esse metodo eh responsavel por lidar com o encontrar itens e adiciona-los no inventario, se possivel */
    public void encontrarItens(Personagem jogador, Scanner input) {
        Random random = new Random();

        Item itemEcontrado;

        if (!recursos.isEmpty()) {

            // o item encontrado vai ser um item escolhido aleatóriamente da lista de recursos (por agora)
            itemEcontrado = recursos.get(random.nextInt(recursos.size()));

            System.out.printf("Você encontrou %s%n", itemEcontrado.getNome());

            int espaco = jogador.getInvEspaco();
            int peso = jogador.getInvPeso();
           
            if (++espaco <= jogador.getInvEspDisp() && ++peso <= jogador.getInvPesoTot()) {
                System.out.printf("Você deseja guardar esse item? s/n :");
                String ans = input.nextLine();

                if (ans.equals("s")) {
                    jogador.addItemInventario(itemEcontrado);
                    System.out.printf("%s foi adicionado ao seu inventário%n", itemEcontrado.getNome());
                }

                recursos.removeLast();
            } else {
                if (++espaco > jogador.getInvEspaco()) {
                    System.out.println("Você não tem mais espaço no inventário");
                }

                if (++peso > jogador.getInvPesoTot()) {
                    System.out.println("Você já atingiu o limite máximo de peso que pode carregar");
                }
            }

        } else {
            System.out.println("Não há mais recursos disponíveis.");
        }
    }

    // essa eh a funcao que vai lidar com as acoes do jogador no ambiente
    public void explorar(Personagem jogador, Scanner input) {
        Random random = new Random();
        System.out.printf("Você adentra %s%n", getDescricao());

        int teste = jogador.getExploracao() + random.nextInt(10);
        int dificuldade = getDificuldadeExploracao(); // vai printar um ngc na tela

        if (teste >= dificuldade) {
            System.out.println("Você está adentrando o ambiente com sucesso");
            System.out.println("O que você deseja fazer durante a exploração?");
            System.out.printf("1 - Econtrar Itens%n" +
                    "2 - Coletar Recursos%n" +
                    "3 - Procurar Abrigo%n" +
                    "Sua resposta: ");

            int resposta = input.nextInt();
            input.nextLine();

            if (resposta == 1) {
                // TODO ajeitar esse encontro de itens
                encontrarItens(jogador, input);
            } else if (resposta == 2) {
                // TODO implementar encontrar recursos
                encontrarItens(jogador, input);
            } else if (resposta == 3) {
                // TODO implementar encontrar abrigo
                // o jogador tem um atributo protegido, que eh um booleano, entao so mudar esse atributo
                System.out.println("Não encontrou nenhum abrigo.");
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

    public void informacao() {
        System.out.printf("Você chegou num ambiente de %s.%n", this.nome);
        System.out.printf("%s%n", this.descricao);
    }

    public void gerarEvento() {}

    public void modificarClima() {
        Random random = new Random();
        this.climaAtual = climasPossiveis.get(random.nextInt(climasPossiveis.size()));
    }

    // gets

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
