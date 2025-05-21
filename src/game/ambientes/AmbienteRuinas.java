package game.ambientes;

import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.alimentos.Agua;
import game.itens.materiais.Madeira;
import game.itens.remedios.Curativo;

public class AmbienteRuinas extends Ambiente {
    private TileManager tileM;

    public AmbienteRuinas(TileManager tileM, GamePanel gp) {
        super("Ruinas",
                "Um local repleto de ruínas e construções abandonadas.",
                9, tileM, gp);
    }

    public void carregarAmbiente(String path) {
        super.carregarAmbiente(path);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    // TODO implementar os itens encontrados nas ruinas

    public void adicionarItens() {

    }

    public void adicionarRecursos() {

    }

    public int getDificuldadeExploracao() {
        System.out.println("Você tem que andar com cautela devido a grande quantidade de entulho.");
        return super.getDificuldadeExploracao();
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }
}
