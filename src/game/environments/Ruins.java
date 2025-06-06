package game.environments;

import game.entity.enemies.*;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;
import game.itens.food.Coffee;
import game.itens.food.Meat;
import game.itens.food.Soup;
import game.itens.remedios.Bandages;
import game.itens.remedios.FirstAidKit;

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
                addItem(new Bandages(), 13 * tileSize, 16 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 4 * tileSize, 9 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 21 * tileSize, 23 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 26 * tileSize, 9 * tileSize);
                break;
            case 2:
                setEnvPath("/maps/city2.csv");
                addItem(new Coffee(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBlue(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/city3.csv");
                addItem(new FirstAidKit(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 13 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBlue(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/city4.csv");
                addItem(new Soup(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 13 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 5:
                setEnvPath("/maps/city5.csv");
                addItem(new Bandages(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBrown(getGp()), 13 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
            case 6:
                setEnvPath("/maps/city6.csv");
                addItem(new Meat(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBlue(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBrown(getGp()), 13 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                break;
        }
    }
}
