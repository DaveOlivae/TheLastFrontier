package game.environments;

import game.entity.enemies.EN_RaiderPurple;
import game.entity.npcs.NPC_Generic1;
import game.entity.npcs.NPC_Generic2;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;

public class City extends Environment{
    public City(TileManager tileM, GamePanel gp, int instance) {
        super("city", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 1:
                setEnvPath("/maps/city1.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addNPC(new NPC_Generic1(getGp()), 14 * tileSize, 13 * tileSize);
                break;
            case 2:
                setEnvPath("/maps/city2.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);

                addNPC(new NPC_Generic1(getGp()), 14 * tileSize, 13 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/city3.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);

                addNPC(new NPC_Generic2(getGp()), 14 * tileSize, 13 * tileSize);
                addNPC(new NPC_Generic1(getGp()), 14 * tileSize, 13 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/city4.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);

                addNPC(new NPC_Generic1(getGp()), 14 * tileSize, 13 * tileSize);
                break;
            case 5:
                setEnvPath("/maps/city5.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);

                addNPC(new NPC_Generic1(getGp()), 14 * tileSize, 13 * tileSize);
                break;
            case 6:
                setEnvPath("/maps/city6.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);

                addNPC(new NPC_Generic1(getGp()), 14 * tileSize, 13 * tileSize);
                break;
        }
    }
}
