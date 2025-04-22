package jogo.ambientes;

import jogo.itens.alimentos.Liquen;
import jogo.itens.materiais.*;

public class AmbienteMontanha extends Ambiente{
    public AmbienteMontanha() {
        super("Montanhas",
                "Um ambiente montanhoso e de difícil travessia",
                7);
    }

    public void atualizarClimas() {
        adicionarClima("Nevando");
        adicionarClima("Ventos fortes");
        adicionarClima("Ensolarado");
    }

    public void adicionarItens() {
        adicionarItem(new Liquen(), 1);
    }

    public void adicionarRecursos() {
        adicionarRecurso(new MinerioFerro(), 10);
        adicionarRecurso(new Carvao(), 7);
        adicionarRecurso(new Pedra(), 1);
    }

    public int getDificuldadeExploracao() {
        System.out.println("O terreno acidentado dificulta sua exploração.");
        return super.getDificuldadeExploracao();
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
