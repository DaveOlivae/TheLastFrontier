package game.ambientes;

import game.itens.alimentos.*;
import game.itens.materiais.Pedra;

public class AmbienteLagoRio extends Ambiente{

    public AmbienteLagoRio() {
        super("Lago e Rio",
                "Uma planície em que um rio desagua num grande lago.",
                6);
    }

    public void atualizarClimas() {
        adicionarClima("Ensolarado");
        adicionarClima("Úmido");
        adicionarClima("Chuva");
    }

    public void adicionarItens() {
        adicionarItem(new Agua(1, 70, 1), 1);
        adicionarItem(new Peixe(), 2);
    }

    public void adicionarRecursos() {
        adicionarRecurso(new Pedra(), 1);
    }

    public int getDificuldadeExploracao() {
        System.out.println("O terreno lamacento dificulta um pouco sua movimentação.");
        return super.getDificuldadeExploracao();
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
