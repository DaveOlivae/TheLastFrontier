package game.environments;

import game.graphics.GamePanel;
import game.graphics.TileManager;

public class City extends Environment{
    public City(TileManager tileM, GamePanel gp, int instance) {
        super("city", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {

    }
}
