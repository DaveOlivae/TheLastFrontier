package game.ambientes;

import game.graphics.GamePanel;
import game.graphics.Tile;
import game.graphics.TileManager;
import game.itens.alimentos.Liquen;
import game.itens.materiais.*;

public class AmbienteMontanha extends Ambiente {

    public AmbienteMontanha(TileManager tileM, GamePanel gp) {
        super("Montanhas",
                "Um ambiente montanhoso e de difícil travessia",
                13, tileM, gp);
    }

    public void carregarAmbiente(String path) {
        super.carregarAmbiente(path);
    }

    public void atualizarClimas() {
        adicionarClima("Nevando");
        adicionarClima("Ventos fortes");
        adicionarClima("Ensolarado");
    }

    public void adicionarItens() {

    }

    public void adicionarRecursos() {
        adicionarRecurso(new MinerioFerro(), 10);
        adicionarRecurso(new Carvao(), 7);
        adicionarRecurso(new Pedra(), 1);
    }

    public int getDificuldadeExploracao() {
        System.out.println("O terreno acidentado dificulta sua exploração.");
        return super.getDificuldadeExploracao();
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
