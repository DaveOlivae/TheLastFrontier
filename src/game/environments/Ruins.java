package game.environments;

import game.entity.enemies.EN_RaiderGreen;
import game.entity.enemies.EN_RaiderPurple;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;

public class Ruins extends Environment {

    public Ruins(TileManager tileM, GamePanel gp, int instance) {
        super("ruins", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 1:
                setEnvPath("/maps/city1.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 2:
                setEnvPath("/maps/city2.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/city3.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/city4.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 5:
                setEnvPath("/maps/city5.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 6:
                setEnvPath("/maps/city6.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
        }
    }
}
