package ambientes;

import itens.alimentos.*;

public class AmbienteFloresta extends Ambiente{
    public AmbienteFloresta() {
        super("Floresta",
                "Um local de vegetação densa e fauna abundante",
                50);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    public void adicionarRecursos() {
        adicionarItem(new Fruta("Maçã"));
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
