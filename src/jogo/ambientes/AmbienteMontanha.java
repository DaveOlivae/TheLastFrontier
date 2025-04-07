package jogo.ambientes;

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

    public void adicionarRecursos() {
        adicionarItem(new MinerioFerro());
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
