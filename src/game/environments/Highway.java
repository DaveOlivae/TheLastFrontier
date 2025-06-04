package game.environments;

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
            case 3:
                setEnvPath("/maps/highway3.csv");
                break;
            case 4:
                setEnvPath("/maps/highway4.csv");
                break;
            case 5:
                setEnvPath("/maps/highway5.csv");
                break;
            case 6:
                setEnvPath("/maps/highway6.csv");
                break;
            case 7:
                setEnvPath("/maps/highway7.csv");
                break;
            case 8:
                setEnvPath("/maps/highway8.csv");
                break;
            case 9:
                setEnvPath("/maps/highway9.csv");
                break;
            case 10:
                setEnvPath("/maps/highway10.csv");
                break;
            case 11:
                setEnvPath("/maps/highway11.csv");
                break;
        }
    }
}
