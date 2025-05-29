package game.ambientes;

import game.graphics.GamePanel;
import game.graphics.TileManager;

public class Ruins extends Environment {

    public Ruins(TileManager tileM, GamePanel gp, int instance) {
        super("ruins", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {

    }
}
