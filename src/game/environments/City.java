package game.environments;

import game.entity.enemies.EN_RaiderPurple;
import game.entity.npcs.NPC_Generic1;
import game.entity.npcs.NPC_Generic2;
import game.entity.npcs.NPC_Generic3;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;
import game.itens.remedios.Bandages;
import game.itens.remedios.FirstAidKit;

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
                addItem(new Bandages(), 15 * tileSize, 15 * tileSize);
                addNPC(new NPC_Generic1(getGp()), 5 * tileSize, 11 * tileSize);
                addNPC(new NPC_Generic3(getGp(), 0), 9 * tileSize, 26 * tileSize);
                break;
            case 2:
                setEnvPath("/maps/city2.csv");
                addItem(new FirstAidKit(), 15 * tileSize, 15 * tileSize);
                addNPC(new NPC_Generic2(getGp(), 0), 9 * tileSize, 11 * tileSize);
                addNPC(new NPC_Generic1(getGp()), 20 * tileSize, 27 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/city3.csv");
                addItem(new Bandages(), 15 * tileSize, 15 * tileSize);
                addNPC(new NPC_Generic2(getGp(), 0), 6 * tileSize, 14 * tileSize);
                addNPC(new NPC_Generic3(getGp(), 0), 25 * tileSize, 15 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/city4.csv");
                addItem(new FirstAidKit(), 15 * tileSize, 15 * tileSize);
                addNPC(new NPC_Generic2(getGp(), 0), 6 * tileSize, 14 * tileSize);
                addNPC(new NPC_Generic1(getGp()), 22 * tileSize, 27 * tileSize);
                break;
            case 5:
                setEnvPath("/maps/city5.csv");
                addItem(new Bandages(), 15 * tileSize, 15 * tileSize);

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
