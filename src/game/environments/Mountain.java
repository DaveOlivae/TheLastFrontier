package game.environments;

import game.graphics.GamePanel;
import game.graphics.TileManager;

public class Mountain extends Environment {

    public Mountain(TileManager tileM, GamePanel gp, int instance) {
        super("mountain", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {

    }
}
