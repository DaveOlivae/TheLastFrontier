package ambientes;

import itens.alimentos.*;

public class AmbienteLagoRio extends Ambiente{

    public AmbienteLagoRio() {
        super("Lago e Rio",
                "Uma planície em que um rio desagua num grande lago.",
                25);
    }

    public void adicionarRecursos() {
        adicionarItem(new Agua(1, 70, 1));
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
