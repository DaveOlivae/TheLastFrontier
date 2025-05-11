package game.ambientes;

import game.itens.alimentos.Agua;
import game.itens.materiais.Madeira;
import game.itens.remedios.Curativo;

public class AmbienteRuinas extends Ambiente{
    public AmbienteRuinas() {
        super("Ruinas",
                "Um local repleto de ruínas e construções abandonadas.",
                9);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    // TODO implementar os itens encontrados nas ruinas

    public void adicionarItens() {
        adicionarItem(new Agua(1, 90, 1), 5);
        adicionarItem(new Curativo(), 15);
    }

    public void adicionarRecursos() {
        adicionarItem(new Madeira(), 1);
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
