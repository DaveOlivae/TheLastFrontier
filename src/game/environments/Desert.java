package game.environments;

import game.entity.enemies.EN_RaiderBlue;
import game.entity.enemies.EN_RaiderGreen;
import game.entity.enemies.EN_RaiderPurple;
import game.events.eventoDoencaFerimento.Dehydration;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.Water;
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
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 13 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBlue(getGp()), 16 * tileSize, 15 * tileSize);
                addEvent(new Dehydration(0.001));
                break;
            case 1:
                setEnvPath("/maps/desert1.csv");
                addItem(new Water(), 19 * tileSize, 15 * tileSize);
                addItem(new Water(), 18 * tileSize, 15 * tileSize);
                addEvent(new Dehydration(0.001));
                break;
        }
    }
}
