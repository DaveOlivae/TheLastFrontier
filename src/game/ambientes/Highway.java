package game.ambientes;

import game.graphics.GamePanel;
import game.graphics.TileManager;

public class Highway extends Environment{
    public Highway(TileManager tileM, GamePanel gp, int instance) {
        super("highway", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 0:
                setEnvPath("/maps/highway0.csv");
                break;
            case 1:
                setEnvPath("/maps/highway1.csv");
                break;
        }
    }
}
