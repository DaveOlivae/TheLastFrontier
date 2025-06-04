package game.environments;

import game.entity.enemies.EN_Raider;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;

public class Desert extends Environment{
    public Desert(TileManager tileM, GamePanel gp, int instance) {
        super("desert", tileM, gp);

        setInstance(instance);
    }

    public void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 0:
                setEnvPath("/maps/desert0.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_Raider(getGp()), 14 * tileSize, 15 * tileSize);
                break;
        }
    }
}
