package ambientes;

import itens.alimentos.*;

public class AmbienteFloresta extends Ambiente{
    public AmbienteFloresta() {
        super("Floresta",
                "Um local de vegetação densa e fauna abundante",
                4);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    public void adicionarRecursos() {
        adicionarItem(new Fruta("Maçã"));
    }

    public int getDificuldadeExploracao() {
        System.out.println("A densa quantidade de galhos e arbustos dificulta sua movimentação.");
        return super.getDificuldadeExploracao();
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
