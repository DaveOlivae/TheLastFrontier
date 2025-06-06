package game.environments;

import game.entity.enemies.*;
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
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 1:
                setEnvPath("/maps/highway1.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/highway3.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBrown(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/highway4.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 5:
                setEnvPath("/maps/highway5.csv");
                addEnemy(new EN_RaiderPurple(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 6:
                setEnvPath("/maps/highway6.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 7:
                setEnvPath("/maps/highway7.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 8:
                setEnvPath("/maps/highway8.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 9:
                setEnvPath("/maps/highway9.csv");
                addEnemy(new EN_RaiderOrange(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 10:
                setEnvPath("/maps/highway10.csv");
                addEnemy(new EN_RaiderBlue(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 11:
                setEnvPath("/maps/highway11.csv");
                addEnemy(new EN_RaiderBrown(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
            case 12:
                setEnvPath("/maps/highway12.csv");
                addEnemy(new EN_RaiderBrown(getGp()), 9 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 10 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 25 * tileSize, 5 * tileSize);
                break;
        }
    }
}
