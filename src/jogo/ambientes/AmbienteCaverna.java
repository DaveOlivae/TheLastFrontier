package jogo.ambientes;

import jogo.itens.ferramentas.Ferramenta;
import jogo.itens.ferramentas.Lanterna;
import jogo.itens.ferramentas.Tocha;
import jogo.itens.materiais.MinerioFerro;
import jogo.personagens.Personagem;

public class AmbienteCaverna extends Ambiente{
    public AmbienteCaverna() {
        super("Caverna",
                "Um ambiente subterrâneo e muito escuro.",
                10);  // coloquei como 10, o mais dificil, caso o usuario tiver uma lanterna ou tocha ai diminui
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
        adicionarClima("Chuva");
    }

    public void adicionarRecursos() {
        adicionarItem(new MinerioFerro());
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }

    // TODO diminuir a quantidade de usos de um item apos uso

    // esse metodo verifica se o jogador tem uma tocha ou lanterna equipada, se sim, ele diminui a dificuldade de exploracao
    public int getDificuldadeExploracao(Personagem jogador) {
        if (jogador.getItemEquipado() instanceof Tocha || jogador.getItemEquipado() instanceof Lanterna) {
            Ferramenta ferramenta = (Ferramenta) jogador.getItemEquipado();  // downcasting pra acessar o metodo geteficiencia

            System.out.printf("Sua %s ilumina a caverna%n", ferramenta.getNome());
            return super.getDificuldadeExploracao() + ferramenta.getEficiencia();
        } else {
            return super.getDificuldadeExploracao();
        }
    }
}
