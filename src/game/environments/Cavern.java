package game.environments;

import game.graphics.GamePanel;
import game.graphics.TileManager;

public class Cavern extends Environment {

    public Cavern(TileManager tileM, GamePanel gp, int instance) {
        super("cavern", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {

    }

}
