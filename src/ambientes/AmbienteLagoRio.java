package ambientes;

import itens.alimentos.*;

public class AmbienteLagoRio extends Ambiente{

    public AmbienteLagoRio() {
        super("Lago e Rio",
                "Uma planície em que um rio desagua num grande lago.",
                3);
    }

    public void atualizarClimas() {
        adicionarClima("Ensolarado");
        adicionarClima("Úmido");
    }

    public void adicionarRecursos() {
        adicionarItem(new Agua(1, 70, 1));
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
