package game.ambientes;

import game.graphics.Tile;
import game.graphics.TileManager;
import game.itens.alimentos.*;
import game.itens.materiais.Pedra;

public class AmbienteLagoRio extends Ambiente {

    public AmbienteLagoRio(TileManager tileM) {
        super("Lago e Rio",
                "Uma planície em que um rio desagua num grande lago.",
                6, tileM);
    }

    public void carregarAmbiente(String path) {
        super.carregarAmbiente(path);
    }

    public void atualizarClimas() {
        adicionarClima("Ensolarado");
        adicionarClima("Úmido");
        adicionarClima("Chuva");
    }

    public void adicionarItens() {

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
