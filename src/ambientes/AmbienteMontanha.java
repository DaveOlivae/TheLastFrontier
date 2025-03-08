package ambientes;

import itens.materiais.*;

public class AmbienteMontanha extends Ambiente{
    public AmbienteMontanha() {
        super("Montanhas",
                "Um ambiente de difícil travessia",
                75,
                "seco");
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
