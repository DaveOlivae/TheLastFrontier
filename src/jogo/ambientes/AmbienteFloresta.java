package jogo.ambientes;

import jogo.itens.alimentos.*;
import jogo.itens.materiais.Madeira;
import jogo.itens.materiais.Pedra;

public class AmbienteFloresta extends Ambiente{
    public AmbienteFloresta() {
        super("Floresta",
                "Um local de vegetação densa e fauna abundante",
                4);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    public void adicionarItens() {
        adicionarItem(new Fruta("Maçã"), 3);
        adicionarItem(new Agua(1, 80, 1), 7);
    }

    public void adicionarRecursos() {
        adicionarRecurso(new Madeira(), 1);
        adicionarRecurso(new Pedra(), 1);
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
