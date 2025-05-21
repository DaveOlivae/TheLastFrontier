package game.ambientes;

import game.entity.NPC_OldMan;
import game.graphics.GamePanel;
import game.graphics.Tile;
import game.graphics.TileManager;
import game.itens.alimentos.*;
import game.itens.materiais.Madeira;
import game.itens.materiais.Pedra;

public class AmbienteFloresta extends Ambiente {

    public AmbienteFloresta(TileManager tileM, GamePanel gp) {
        super("Floresta",
                "Um local de vegetação densa e fauna abundante",
                5, tileM, gp);
        adicionarItens();
        adicionarNPCs();
    }

    public void adicionarNPCs() {
        super.adicionarNPC(new NPC_OldMan(getGp(), 9 * getGp().tileSize, 8 * getGp().tileSize));
    }

    public void carregarAmbiente(String path) {
        super.carregarAmbiente(path);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
    }

    public void adicionarItens() {
        adicionarItem(new Fruta("Maçã"), (19 * 64), (20 * 64));
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
