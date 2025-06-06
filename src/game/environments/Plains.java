package game.environments;

import game.entity.enemies.*;
import game.events.eventoDoencaFerimento.Fever;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.food.Apple;
import game.itens.food.Pomegranate;
import game.itens.food.Strawberry;

public class Plains extends Environment{
    public Plains(TileManager tileM, GamePanel gp, int instance) {
        super("plains", tileM, gp);

        setInstance(instance);
    }

    public void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 0:
                setEnvPath("/maps/plains0.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.009));
                break;
            case 1:
                setEnvPath("/maps/plains1.csv");
                addItem(new Pomegranate(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderOrange(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 2:
                setEnvPath("/maps/plains2.csv");
                addItem(new Strawberry(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderGreen(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 3:
                setEnvPath("/maps/plains3.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBlue(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 4:
                setEnvPath("/maps/plains4.csv");
                addItem(new Pomegranate(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPink(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 5:
                setEnvPath("/maps/plains5.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBrown(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 6:
                setEnvPath("/maps/plains6.csv");
                addItem(new Strawberry(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderPurple(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 7:
                setEnvPath("/maps/plains7.csv");
                addItem(new Strawberry(), 15 * tileSize, 15 * tileSize);
                addEnemy(new EN_RaiderBrown(getGp()), 14 * tileSize, 15 * tileSize);
                addEvent(new Fever(0.0009));
                break;
            case 8:
                setEnvPath("/maps/plains8.csv");
                addItem(new Apple(), 15 * tileSize, 15 * tileSize);
                break;
        }
    }
}
