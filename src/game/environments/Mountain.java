package game.environments;

import game.entity.enemies.*;
import game.events.eventoDoencaFerimento.Hypotermia;
import game.graphics.GamePanel;
import game.graphics.TileManager;

public class Mountain extends Environment {

    public Mountain(TileManager tileM, GamePanel gp, int instance) {
        super("mountain", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 0:
                setEnvPath("/maps/mountain0.csv");
                addEvent(new Hypotermia(0.001));
                addEnemy(new EN_RaiderBlue(getGp()), 3 * tileSize, 21 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 3 * tileSize, 21 * tileSize);
                break;
            case 1:
                setEnvPath("/maps/mountain1.csv");
                addEvent(new Hypotermia(0.001));
                addEnemy(new EN_RaiderBlue(getGp()), 3 * tileSize, 21 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 3 * tileSize, 21 * tileSize);
                break;
            case 2:
                setEnvPath("/maps/mountain2.csv");
                addEvent(new Hypotermia(0.001));
                addEnemy(new EN_RaiderBlue(getGp()), 3 * tileSize, 21 * tileSize);
                addEnemy(new EN_RaiderBrown(getGp()), 3 * tileSize, 21 * tileSize);
                break;
            case 3:
                setEnvPath("/maps/mountain3.csv");
                addEvent(new Hypotermia(0.001));
                addEnemy(new EN_RaiderBlue(getGp()), 3 * tileSize, 21 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 3 * tileSize, 21 * tileSize);
                break;
            case 4:
                setEnvPath("/maps/mountain4.csv");
                addEvent(new Hypotermia(0.001));
                addEnemy(new EN_RaiderBlue(getGp()), 3 * tileSize, 21 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 3 * tileSize, 21 * tileSize);
                break;
            case 5:
                setEnvPath("/maps/mountain5.csv");
                addEvent(new Hypotermia(0.001));
                addEnemy(new EN_RaiderBlue(getGp()), 3 * tileSize, 21 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 3 * tileSize, 21 * tileSize);
                break;
            case 6:
                setEnvPath("/maps/mountain6.csv");
                addEvent(new Hypotermia(0.001));
                break;
        }
    }
}
