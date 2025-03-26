package ambientes;

import itens.materiais.MinerioFerro;

public class AmbienteRuinas extends Ambiente{
    public AmbienteRuinas() {
        super("Ruinas",
                "Um ambiente subterrâneo e muito escuro.",
                100);
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
}
