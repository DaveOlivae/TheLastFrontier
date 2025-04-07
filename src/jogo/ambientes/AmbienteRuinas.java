package jogo.ambientes;

import jogo.itens.materiais.MinerioFerro;

public class AmbienteRuinas extends Ambiente{
    public AmbienteRuinas() {
        super("Ruinas",
                "Um local repleto de ruínas e construções abandonadas.",
                5);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    public void adicionarRecursos() {
        adicionarItem(new MinerioFerro());
    }

    public int getDificuldadeExploracao() {
        System.out.println("Você tem que andar com cautela devido a grande quantidade de entulho.");
        return super.getDificuldadeExploracao();
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
