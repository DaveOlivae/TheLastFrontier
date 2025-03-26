import ambientes.*;
import personagens.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Personagem jogador;

        GerenciadorDeAmbientes gerenciadorAmbientes = new GerenciadorDeAmbientes();

        Ambiente ambiente;

        // title card
        System.out.println("Welcome to The Last Frontier!");

        // name input
        System.out.print("Name your character: ");
        String name = input.nextLine();

        // class input
        System.out.printf("Select a class:%n1 - Rastreador%n2 - Mecânico%n3 - Médico%n4 - Sobrevivente Nato%n");

        int answer;
        System.out.print("Your choice is: ");

        while (true) {
            // checar se a entrada eh um inteiro
            if (input.hasNextInt()) {
                answer = input.nextInt();

                if (answer >= 1 && answer <= 4) {
                    break;
                } else {
                    System.out.print("Please, enter a valid choice. ");
                }
            } else {
                System.out.print("Please, enter a valid choice. ");
                input.next();  // consome a entrada q n eh um inteiro
            }
        }

        input.nextLine(); // consome o new line

        // creates the player
        if (answer == 1) {
            jogador = new Rastreador(name);
        } else if (answer == 2) {
            jogador = new Mecanico(name);
        } else if (answer == 3) {
            jogador = new Medico(name);
        } else {
            jogador = new SobreviventeNato(name);
        }

        // mensagem de boa sorte e mostra os atributos iniciais do jogador
        System.out.printf("Good Luck %s!%n", jogador.getName());
        System.out.println("Here are your attributes: ");
        jogador.showAttributes();

        /* ------------- Main game loop ---------------- */
        int turnos = 1;
        while (true) {

            /* Fase de inicio */

            // muda o ambiente e mostra informacoes sobre o ambiente
            ambiente = gerenciadorAmbientes.mudarAmbiente();
            ambiente.informacao();

            ambiente.modificarClima();
            System.out.printf("O clima está %s%n", ambiente.getClimaAtual());
            /* Fase de acao */

            while (true) {


                // gives the player options
                System.out.printf("What do you wish to do?%n" +
                        "1 - Continue walking%n" +
                        "2 - Explore%n" +
                        "3 - Check Inventory%n" +
                        "4 - Check Attributes%n" +
                        "Your answer: ");
                int answer2 = input.nextInt();
                input.nextLine();

                if (answer2 == 1) {
                    // player decides to do nothing
                    break;
                } else if (answer2 == 2) {
                    // player decides to explore
                    ambiente.explorar(jogador);
                } else if (answer2 == 3) {
                    /* inventory options */
                    while (true) {
                        System.out.printf("O que você deseja fazer?%n" +
                                "1 - Ver itens%n" +
                                "2 - Inspecionar itens%n" +
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
                } else {
                    jogador.showAttributes();
                }
            }

            /* fase de evento aleatorio */



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
                System.out.println("The game has ended, thank you for playing!");
                break;
            }

            turnos++;
        }

        input.close();
    }
}