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

        System.out.printf("Good Luck %s!%n", jogador.getName());

        /* ------------- Main game loop ---------------- */
        int turnos = 1;
        while (true) {

            /* Fase de inicio */
            System.out.println("Here are your attributes: ");
            jogador.showAttributes();

            ambiente = gerenciadorAmbientes.mudarAmbiente();
            ambiente.informacao();

            while (true) {
                System.out.printf("What do you wish to do?%n" +
                        "1 - Continue walking%n" +
                        "2 - Explore%n" +
                        "3 - Check Inventory%n" +
                        "4 - Check Attributes%n" +
                        "Your answer: ");
                int answer2 = input.nextInt();
                input.nextLine();

                if (answer2 == 1) {
                    break;
                } else if (answer2 == 2) {
                    ambiente.explorar(jogador);
                } else if (answer2 == 3) {
                    while (true) {
                        System.out.printf("What do you wish to do?%n" +
                                "1 - See itens%n" +
                                "2 - Inspect item%n" +
                                "3 - Remove item%n" +
                                "4 - Cancel%n" +
                                "Your answer: ");
                        int answer3 = input.nextInt();

                        if (answer3 == 1) {
                            jogador.mostrarInventario();
                        } else if (answer3 == 2){
                            jogador.inspecionarItem();
                            break;
                        }
                    }
                } else {
                    jogador.showAttributes();
                }
            }

            if (turnos == 2) {
                System.out.println("The game has ended, thank you for playing!");
                break;
            }

            turnos++;
        }

        input.close();
    }
}