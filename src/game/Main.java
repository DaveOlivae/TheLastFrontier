package game;

import game.graphics.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();  // creates the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close when 'x' pressed
        window.setResizable(false);  // fixed window size
        window.setTitle("The Last Frontier");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);  // window appears at the center
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

//        Scanner input = new Scanner(System.in);
//
//        Personagem jogador;
//
//        GerenciadorDeAmbientes gerenciadorAmbientes = new GerenciadorDeAmbientes();
//
//        GerenciadorDeEventos gerenciadorDeEventos = new GerenciadorDeEventos();
//
//        Ambiente ambiente;
//
//        // === Titulo ===
//        System.out.println("Bem-vindo a The Last Frontier!");
//
//        // === Pede o nome ===
//        System.out.print("Qual seu nome? ");
//        String name = input.nextLine();
//
//        // === Pergunta a classe ===
//        System.out.printf("Selecione uma classe:%n" +
//                "\t1 - Rastreador%n" +
//                "\t2 - Mecânico%n" +
//                "\t3 - Médico%n" +
//                "\t4 - Sobrevivente Nato%n" +
//                "Sua resposta: ");
//
//
//        // === checa se a entrada eh valida (um inteiro de 1 a 4) ===
//        int answer;
//        while (true) {
//            if (input.hasNextInt()) {
//                answer = input.nextInt();
//
//                if (answer >= 1 && answer <= 4) {
//                    break;
//                } else {
//                    System.out.print("Por favor, insira uma resposta válida.");
//                }
//            } else {
//                System.out.print("Por favor, insira uma resposta válida.");
//                input.next();  // consome a entrada q n eh um inteiro
//            }
//        }
//
//        input.nextLine(); // consome o new line
//
//        // === cria o jogador ===
//        if (answer == 1) {
//            jogador = new Rastreador(name);
//        } else if (answer == 2) {
//            jogador = new Mecanico(name);
//        } else if (answer == 3) {
//            jogador = new Medico(name);
//        } else {
//            jogador = new SobreviventeNato(name);
//        }
//
//        // === mensagem de boa sorte e mostra os atributos iniciais do jogador ===
//        System.out.printf("Boa sorte %s!%n", jogador.getName());
//        System.out.println("===== Seus atributos =====");
//        jogador.showAttributes();
//
//        /* ------------- Main game loop ---------------- */
//
//        int turnos = 1;
//
//        boolean caminhar = false;  // essa flag serve pra mudar o ambiente quando o jogador escolhe andar
//
//        ambiente = gerenciadorAmbientes.mudarAmbiente();  // inicia o ambiente
//
//        while (true) {
//
//            /* Fase de inicio */
//
//            // muda o ambiente e mostra informacoes sobre o ambiente
//
//            if (caminhar) {
//                ambiente = gerenciadorAmbientes.mudarAmbiente();
//            }
//
//            ambiente.informacao();
//
//            ambiente.modificarClima();
//            System.out.printf("O clima está %s%n", ambiente.getClimaAtual());
//            /* Fase de acao */
//
//            // gives the player options
//            // continuar a caminhar levara o jogador para outro ambiente e passara o turno
//            // explorar da ao jogador a oportunidade de encontrar recursos ou abrigo
//
//            while (true) {
//                System.out.printf("O que você deseja fazer?%n" +
//                        "\t1 - Continuar a caminhar ( - 10 de energia )%n" +
//                        "\t2 - Explorar ( - 30 de energia )%n" +
//                        "\t3 - Checar o inventário%n" +
//                        "\t4 - Checar atributos%n" +
//                        "Sua resposta: ");
//                int answer2 = input.nextInt();
//                input.nextLine();
//
//                if (answer2 == 1) {
//                    System.out.println("!== O percurso é logo e você se cansa moderadamente. " +
//                            "Perdeu 10 pontos de energia.");
//                    jogador.attEnergia(-10);
//                    caminhar = true;
//                    break;
//                } else if (answer2 == 2) {
//                    // ======================= EXPLORACAO ==========================
//                    // TODO trocar essa descricao
//                    ambiente.explorar(jogador, input);
//                    caminhar = false;
//                    break;
//                } else if (answer2 == 3) {
//                    jogador.acessarInventario(input);
//                } else if (answer2 == 4) {
//                    // =============== MOSTRA ATRIBUTOS =================
//                    jogador.showAttributes();
//                }
//            }
//            /* fase de evento aleatorio */
//
//            gerenciadorDeEventos.atualizarListaDeEventos(ambiente, jogador, caminhar);
//            gerenciadorDeEventos.sortearEvento(ambiente, jogador);
//
//            /* fase de manutencao */
//            jogador.attFomeSede();
//            System.out.println("Você está ficando com fome e sede");
//
//            if (jogador.getEnergia() == 0) {
//                System.out.println("Sua energia chegou a zero e você morreu de exaustão. Fim de jogo");
//                break;
//            }
//
//            // resolvi colocar como fome max 80 e sede max 60, talvez mude dps
//            if (jogador.getFome() >= 80) {
//                System.out.println("Você morreu de fome. Fim de jogo");
//                break;
//            }
//
//            if (jogador.getSede() >= 60) {
//                System.out.println("Você morreu de sede. Fim de jogo");
//                break;
//            }
//
//            if (turnos == 30) {
//                System.out.println("O jogo acabou. Obrigado por jogar!");
//                break;
//            }
//
//            turnos++;
//        }
//
//        input.close();
    }
}