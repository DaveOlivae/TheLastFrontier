package game.itens;

import game.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    private List<Item> itens;
    private int pesoTotal;
    private int espacoDisponivel;
    private int peso;
    private int espaco;

    public Inventario(int pesoTotal, int espacoDisponivel) {
        this.pesoTotal = pesoTotal;
        this.espacoDisponivel = espacoDisponivel;
        this.itens = new ArrayList<>();
    }

    public void acessarInventario(Scanner input, Player jogador) {
        /*
        *   eu nao sei se essa eh a melhor maneira de acessar o inventario, mas atualmente eh a unica que ta
        *   satisfazendo o que quero entao vou manter assim
        * */

        /* ========== OPCOES DO INVENTARIO ============*/
        while (true) {
            System.out.printf("O que você deseja fazer?%n" +
                    "\t1 - Ver itens%n" +
                    "\t2 - Inspecionar itens%n" +
                    "\t3 - Usar item%n" +
                    "\t4 - Remover item%n" +
                    "\t5 - Equipar item%n" +
                    "\t6 - Cancelar%n" +
                    "Sua decisão: ");
            int answer3 = input.nextInt();

            if (answer3 == 1) {
                // player just sees whats in the inventory
                if (emptyInventory()) {
                    mostrarItens(jogador);
                }
            } else if (answer3 == 2) {
                // player sees whats in the inventory and chooses an item
                // the items attributes will be shown
                if (emptyInventory()) {
                    mostrarItens(jogador);

                    System.out.print("Qual item deseja inspecionar? (digite o index) : ");

                    int answer4 = input.nextInt();
                    input.nextLine();

                    inspecionarItem(answer4);
                }

            } else if (answer3 == 3) {
                if (emptyInventory()) {
                    mostrarItens(jogador);

                    System.out.print("Qual item você deseja usar? ");

                    int answer4 = input.nextInt();
                    input.nextLine();

                    usarItem(answer4 - 1, jogador);
                }
            } else if (answer3 == 4) {
                // player escolhe um item para remover do inventario
                if (emptyInventory()) {
                    mostrarItens(jogador);

                    System.out.print("Qual item deseja remover: (digite o index) : ");

                    int answer4 = input.nextInt();
                    input.nextLine();

                    removerItem(answer4 - 1);

                    System.out.println("O item foi removido.");
                }
            } else if (answer3 == 5) {
                for (int i = 0; i < this.itens.size(); i++) {
                    Item item = this.itens.get(i);

                    if (item.isEquipavel()) {
                        System.out.printf("\t%d - %s%n", i, item.getName());
                    }
                }

                System.out.print("Sua resposta: ");
                int answer4 = input.nextInt();
                input.nextLine();

                equiparItemInv(answer4, jogador);
                System.out.printf("%s foi equipado%n", this.itens.get(answer4).getName());

            } else if (answer3 == 6) {
                break;
            }
        }
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        this.peso += item.getPeso();
        this.espaco++;  // a ideia eh que cada item ocupa um espaço mas talvez alguns jogo.itens possam ocupar mais de um
    }

    // esse metodo serve para que o usuario possa selecionar um item
    // as informacoes desse item serao imprimidas
    public void inspecionarItem(int i) {
        getAtributosItem(i);
    }

    public void removerItem(int i) {
        this.itens.remove(i);
    }

    // mudei a assinatura da funcao
    public void usarItem(int i, Player jogador) {
        Item item = this.itens.get(i);
        item.usar(jogador);

        item.diminuirDurabilidade(1);
        if (item.getDurabilidade() == 0) {
            // eu preciso dizer quando o total de usos chegar ao fim e o item nao puder ser mais usado?
            this.itens.remove(i);
        }
    }

    public void equiparItemInv(int i, Player jogador) {
        jogador.equiparItem(this.itens.get(i));
    }

    public boolean emptyInventory() {
        if (itens.isEmpty()) {
            System.out.println("Seu inventário está vazio");
        }
        return !itens.isEmpty();
    }

    public void mostrarItens(Player jogador) {
        System.out.println("Inventário: ");
        for (int i = 1; i <= itens.size(); i++) {
            System.out.println("\t" + i + "- " + itens.get(i - 1).getName());
        }

        try {
            System.out.printf("\t>> Item Equipado: %s%n", jogador.getItemEquipado().getName());
        } catch (NullPointerException e) {
            System.out.println("\t>> Item Equipado: nenhum");
        }
    }

    public void getAtributosItem(int i) {
        itens.get(i - 1).getAttributes();
    }

    public int getPesoTotal() {
        return this.pesoTotal;
    }

    public int getEspacoDisponivel() {
        return this.espacoDisponivel;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getEspaco() {
        return this.espaco;
    }
}
