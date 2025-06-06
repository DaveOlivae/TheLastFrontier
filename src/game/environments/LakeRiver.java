package game.environments;

import game.events.eventoDoencaFerimento.Fever;
import game.graphics.GamePanel;
import game.graphics.TileManager;

public class LakeRiver extends Environment {

    public LakeRiver(TileManager tileM, GamePanel gp, int instance) {
        super("lakeriver", tileM, gp);

        setInstance(instance);
    }

    private void setInstance(int instance) {
        int tileSize = getGp().tileSize;

        switch (instance) {
            case 1:
                setEnvPath("/maps/river1.csv");
                addEvent(new Fever(0.001));
                break;
            case 2:
                setEnvPath("/maps/river2.csv");
                addEvent(new Fever(0.001));
                break;
            case 3:
                setEnvPath("/maps/river3.csv");
                addEvent(new Fever(0.001));
                break;
            case 4:
                setEnvPath("/maps/river4.csv");
                addEvent(new Fever(0.001));
                break;
            case 5:
                setEnvPath("/maps/river5.csv");
                addEvent(new Fever(0.001));
                break;
            case 6:
                setEnvPath("/maps/river6.csv");
                addEvent(new Fever(0.001));
                break;
            case 7:
                setEnvPath("/maps/river7.csv");
                addEvent(new Fever(0.001));
                break;
            case 8:
                setEnvPath("/maps/river8.csv");
                addEvent(new Fever(0.001));
                break;
            case 9:
                setEnvPath("/maps/river9.csv");
                addEvent(new Fever(0.001));
                break;
            case 10:
                setEnvPath("/maps/river10.csv");
                addEvent(new Fever(0.001));
                break;
            case 11:
                setEnvPath("/maps/river11.csv");
                addEvent(new Fever(0.001));
                break;
            case 12:
                setEnvPath("/maps/river12.csv");
                addEvent(new Fever(0.001));
                break;
            case 13:
                setEnvPath("/maps/river13.csv");
                addEvent(new Fever(0.001));
                break;
        }
    }
}
