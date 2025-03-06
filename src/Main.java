import ambientes.*;
import personagens.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Personagem jogador = null;

        GerenciadorDeAmbientes gerenciadorAmbientes = new GerenciadorDeAmbientes();

        Ambiente ambiente = null;

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

        // main game loop
        int turnos = 1;
        while (true) {

            /* Fase de inicio */
            System.out.printf("Here are your attributes:%nLife: %d%nHunger: %d%nThirst: %d%nEnergy: %d%nSanity: %d%n", jogador.getVida(), jogador.getFome(),
                    jogador.getSede(), jogador.getEnergia(), jogador.getSanidade());

            ambiente = gerenciadorAmbientes.mudarAmbiente(ambiente);
            ambiente.informacao();

            System.out.printf("What do you wish to do?%n1 - Nothing%n2 - Explore%n3 - Check Inventory%nYour answer: ");
            int answer2 = input.nextInt();

            if (answer2 == 2) {
                ambiente.explorar(jogador);
            } else if (answer2 == 3) {
                while (true) {
                    System.out.printf("What do you wish to do?%n1 - See itens%n2 - Remove item%n3 - Cancel%nYour answer: ");
                    int answer3 = input.nextInt();

                    if (answer3 == 1) {
                        jogador.mostrarInventario();
                    } else {
                        break;
                    }
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