package ambientes;

import itens.materiais.MinerioFerro;

public class AmbienteCaverna extends Ambiente{
    public AmbienteCaverna() {
        super("Caverna",
                "Um ambiente subterrâneo e muito escuro.",
                100,
                "úmido");
    }

    public void adicionarRecursos() {
        adicionarItem(new MinerioFerro());
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getClima() {
        return super.getClima();
    }

    public String getName() {
        return super.getName();
    }
}
