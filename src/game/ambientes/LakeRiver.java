package game.ambientes;

import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.materiais.Pedra;

public class LakeRiver extends Environment {

    public LakeRiver(TileManager tileM, GamePanel gp, int instance) {
        super("lakeriver", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {

    }
}
