package game.ambientes;

import game.entity.enemies.EN_Raider;
import game.entity.npcs.NPC_OldMan;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.*;
import game.itens.weapons.Revolver;

public class Forest extends Environment {

    public Forest(TileManager tileM, GamePanel gp, int instance) {
        super("forest", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 0:
                setEnvPath("/maps/forest0.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_Raider(getGp()), 14 * tileSize, 15 * tileSize);
                break;
            case 1:
                setEnvPath("/maps/forest1.csv");
                addNPC(new NPC_OldMan(getGp()), 9 * tileSize, 8 * tileSize);
                addEnemy(new EN_Raider(getGp()), 14 * tileSize, 15 * tileSize);
                addItem(new Revolver(), 18 * tileSize, 25 * tileSize);
                break;
            case 2:
                setEnvPath("/maps/forest2.csv");
                addItem(new Apple(), 20 * tileSize, 16 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/forest3.csv");
                addItem(new Apple(), 15 * tileSize, 13 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/forest4.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
        }
    }
}
