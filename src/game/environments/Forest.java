package game.environments;

import game.entity.enemies.EN_Raider;
import game.entity.npcs.NPC_OldMan;
import game.events.eventoCriatura.Snake;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.*;
import game.itens.weapons.Ammo;
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
                addEnemy(new EN_Raider(getGp()), 14 * tileSize, 27 * tileSize);
                addItem(new Revolver(), 18 * tileSize, 25 * tileSize);
                addItem(new Ammo("pistol", 13), 19 * tileSize, 26 * tileSize);
                addEvent(new Snake(0.0001));
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
            case 5:
                setEnvPath("/maps/forest5.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 6:
                setEnvPath("/maps/forest6.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 7:
                setEnvPath("/maps/forest7.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 8:
                setEnvPath("/maps/forest8.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 9:
                setEnvPath("/maps/forest9.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 10:
                setEnvPath("/maps/forest10.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 11:
                setEnvPath("/maps/forest11.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 12:
                setEnvPath("/maps/forest12.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 13:
                setEnvPath("/maps/forest13.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 14:
                setEnvPath("/maps/forest14.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
            case 15:
                setEnvPath("/maps/forest15.csv");
                addItem(new Apple(), 16 * tileSize, 12 * tileSize);
                break;
        }
    }
}
