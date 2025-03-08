package ambientes;

import itens.alimentos.*;

public class AmbienteFloresta extends Ambiente{
    public AmbienteFloresta() {
        super("Floresta",
                "Um local de vegetação densa e fauna abundante",
                50,
                "úmido");
    }

    public void adicionarRecursos() {
        adicionarItem(new Fruta("Maçã"));
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
