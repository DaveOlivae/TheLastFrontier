package jogo.ambientes;

import jogo.itens.alimentos.Agua;
import jogo.itens.armas.Explosivo;
import jogo.itens.armas.Revolver;
import jogo.itens.ferramentas.Ferramenta;
import jogo.itens.ferramentas.Lanterna;
import jogo.itens.ferramentas.Picareta;
import jogo.itens.ferramentas.Tocha;
import jogo.itens.materiais.*;
import jogo.itens.remedios.Curativo;
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
        adicionarRecurso(new MinerioFerro(), 6);
        adicionarRecurso(new MinerioCobre(), 4);
        adicionarRecurso(new Carvao(), 2);
        adicionarRecurso(new Ouro(), 13);
        adicionarRecurso(new Diamante(), 17);
    }

    public void adicionarItens() {
        adicionarItem(new Agua(1, 80, 1), 5);
        adicionarItem(new Picareta(), 10);
        adicionarItem(new Explosivo("TNT", 3, 1, "Dinamite", 15, 20), 15);
        adicionarItem(new Lanterna(), 13);
        adicionarItem(new Curativo(), 7);
        adicionarItem(new Revolver(), 20);
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
