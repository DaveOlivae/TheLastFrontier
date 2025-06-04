package game.environments;

import game.entity.enemies.EN_Raider;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;

public class LakeRiver extends Environment {

    public LakeRiver(TileManager tileM, GamePanel gp, int instance) {
        super("lakeriver", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 1:
                setEnvPath("/maps/river1.csv");
                break;
            case 2:
                setEnvPath("/maps/river2.csv");
                break;
            case 3:
                setEnvPath("/maps/river3.csv");
                break;
            case 4:
                setEnvPath("/maps/river4.csv");
                break;
            case 5:
                setEnvPath("/maps/river5.csv");
                break;
            case 6:
                setEnvPath("/maps/river6.csv");
                break;
            case 7:
                setEnvPath("/maps/river7.csv");
                break;
            case 8:
                setEnvPath("/maps/river8.csv");
                break;
        }
    }
}
