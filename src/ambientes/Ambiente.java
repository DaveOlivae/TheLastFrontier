package ambientes;

// TODO : Implementar dificuldade de exploração
// TODO : Implementar dificuldade de encontrar itens

import itens.*;
import personagens.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public abstract class Ambiente {
    // atributos
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private List<String> climasPossiveis;  // aqui eu to fazendo um lista pra guardar todos os climas
    private String climaAtual;  // esse eh o clima do turno
    private List<Item> recursos;

    // construtor
    public Ambiente(String nome, String descricao, int dificuldadeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.climasPossiveis = new ArrayList<>();
        this.recursos = new ArrayList<>();
        adicionarRecursos();
        atualizarClimas();
    }

    // metodos
    public void adicionarRecursos() {}

    public void atualizarClimas() {}

    public void adicionarItem(Item item) {
        recursos.add(item);
    }

    public void adicionarClima(String clima) {
        climasPossiveis.add(clima);
    }

    /* esse metodo eh responsavel por lidar com o encontrar itens e adiciona-los no inventario, se possivel */
    private void encontrarItens(Personagem jogador, Scanner input) {
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
    public void explorar(Personagem jogador) {
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.printf("Você adentra %s%n", getDescricao());
            System.out.printf("1 - Procurar Itens%n2 - Ir embora%nSua resposta: ");

            int resposta = input.nextInt();
            input.nextLine();

            if (resposta == 1) {
                encontrarItens(jogador, input);
            } else {
                break;
            }
        }
    }

    public void informacao() {
        System.out.printf("Você chegou num ambiente de %s.%n", this.nome);
        System.out.printf("%s%n", this.descricao);
    }

    public void gerarEvento() {}

    public void modificarClima() {
        Random random = new Random();
        this.climaAtual = climasPossiveis.get(random.nextInt(climasPossiveis.size() - 1));
    }

    // gets

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
