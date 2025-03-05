import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Personagem jogador = null;

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

            System.out.printf("What do you wish to do?%n 1 - Nothing%nYour answer: ");
            int ans = input.nextInt();

            if (turnos == 2) {
                System.out.println("The game has ended, thank you for playing!");
                break;
            }

            turnos++;
        }

        input.close();
    } 
}
