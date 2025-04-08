package jogo;

import jogo.ambientes.*;
import jogo.eventos.GerenciadorDeEventos;
import jogo.personagens.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Personagem jogador;

        GerenciadorDeAmbientes gerenciadorAmbientes = new GerenciadorDeAmbientes();

        GerenciadorDeEventos gerenciadorDeEventos = new GerenciadorDeEventos();

        Ambiente ambiente;

        // === Titulo ===
        System.out.println("Bem-vindo a The Last Frontier!");

        // === Pede o nome ===
        System.out.print("Qual seu nome? ");
        String name = input.nextLine();

        // === Pergunta a classe ===
        System.out.printf("Selecione uma classe:%n" +
                "1 - Rastreador%n" +
                "2 - Mecânico%n" +
                "3 - Médico%n" +
                "4 - Sobrevivente Nato%n" +
                "Sua resposta: ");


        // === checa se a entrada eh valida (um inteiro de 1 a 4) ===
        int answer;
        while (true) {
            if (input.hasNextInt()) {
                answer = input.nextInt();

                if (answer >= 1 && answer <= 4) {
                    break;
                } else {
                    System.out.print("Por favor, insira uma resposta válida.");
                }
            } else {
                System.out.print("Por favor, insira uma resposta válida.");
                input.next();  // consome a entrada q n eh um inteiro
            }
        }

        input.nextLine(); // consome o new line

        // === cria o jogador ===
        if (answer == 1) {
            jogador = new Rastreador(name);
        } else if (answer == 2) {
            jogador = new Mecanico(name);
        } else if (answer == 3) {
            jogador = new Medico(name);
        } else {
            jogador = new SobreviventeNato(name);
        }

        // === mensagem de boa sorte e mostra os atributos iniciais do jogador ===
        System.out.printf("Boa sorte %s!%n", jogador.getName());
        System.out.println("===== Seus atributos =====");
        jogador.showAttributes();

        /* ------------- Main game loop ---------------- */

        int turnos = 1;

        boolean caminhar = false;  // essa flag serve pra mudar o ambiente quando o jogador escolhe andar

        ambiente = gerenciadorAmbientes.mudarAmbiente();  // inicia o ambiente

        while (true) {

            /* Fase de inicio */

            // muda o ambiente e mostra informacoes sobre o ambiente

            if (caminhar) {
                ambiente = gerenciadorAmbientes.mudarAmbiente();
            }

            ambiente.informacao();

            ambiente.modificarClima();
            System.out.printf("O clima está %s%n", ambiente.getClimaAtual());
            /* Fase de acao */

            // gives the player options
            // continuar a caminhar levara o jogador para outro ambiente e passara o turno
            // explorar da ao jogador a oportunidade de encontrar recursos ou abrigo

            while (true) {
                System.out.printf("O que você deseja fazer?%n" +
                        "1 - Continuar a caminhar%n" +
                        "2 - Explorar%n" +
                        "3 - Checar o inventário%n" +
                        "4 - Checar atributos%n" +
                        "Sua resposta: ");
                int answer2 = input.nextInt();
                input.nextLine();

                if (answer2 == 1) {
                    caminhar = true;
                    break;
                } else if (answer2 == 2) {
                    // ======================= EXPLORACAO ==========================
                    // TODO consertar exploracao
                    // TODO trocar essa descricao
                    ambiente.explorar(jogador, input);
                    caminhar = false;
                    break;
                } else if (answer2 == 3) {
                    /* ========== OPCOES DO INVENTARIO ============*/
                    while (true) {
                        System.out.printf("O que você deseja fazer?%n" +
                                "1 - Ver jogo.itens%n" +
                                "2 - Inspecionar jogo.itens%n" +
                                "3 - Usar item%n" +
                                "4 - Remover item%n" +
                                "5 - Cancelar%n" +
                                "Sua decisão: ");
                        int answer3 = input.nextInt();

                        if (answer3 == 1) {
                            // player just sees whats in the inventory
                            if (!jogador.playerInvEmpty()) {
                                jogador.mostrarInventario();
                            }
                        } else if (answer3 == 2) {
                            // player sees whats in the inventory and chooses an item
                            // the items attributes will be shown
                            if (jogador.playerInvEmpty()) {
                                jogador.mostrarInventario();

                                System.out.print("Qual item deseja inspecionar? (digite o index) : ");

                                int answer4 = input.nextInt();
                                input.nextLine();

                                jogador.inspecionarItem(answer4);
                            }

                        } else if (answer3 == 3) {
                            if (jogador.playerInvEmpty()) {
                                jogador.mostrarInventario();

                                System.out.print("Qual item você deseja usar? ");

                                int answer4 = input.nextInt();
                                input.nextLine();

                                jogador.usarItem(answer4 - 1, jogador);
                            }
                        } else if (answer3 == 4) {
                            // player escolhe um item para remover do inventario
                            if (jogador.playerInvEmpty()) {
                                jogador.mostrarInventario();

                                System.out.print("Qual item deseja remover: (digite o index) : ");

                                int answer4 = input.nextInt();
                                input.nextLine();

                                jogador.remvItemInventario(answer4 - 1);

                                System.out.println("O item foi removido.");
                            }

                        } else if (answer3 == 5) {
                            break;
                        }
                    }
                } else if (answer2 == 4) {
                    // =============== MOSTRA ATRIBUTOS =================
                    jogador.showAttributes();
                }
            }
            /* fase de evento aleatorio */

            gerenciadorDeEventos.atualizarListaDeEventos(ambiente, jogador, caminhar);
            gerenciadorDeEventos.sortearEvento(ambiente, jogador);

            /* fase de manutencao */
            jogador.attFomeSede();
            System.out.println("Você está ficando com fome e sede");

            // resolvi colocar como fome max 80 e sede max 60, talvez mude dps
            if (jogador.getFome() >= 80) {
                System.out.println("Você morreu de fome. Fim de jogo");
                break;
            }

            if (jogador.getSede() >= 60) {
                System.out.println("Você morreu de sede. Fim de jogo");
                break;
            }

            if (turnos == 30) {
                System.out.println("O jogo acabou. Obrigado por jogar!");
                break;
            }

            turnos++;
        }

        input.close();
    }
}