package game.events;

import game.environments.Environment;
import game.graphics.GamePanel;

import java.util.List;

public class EventManager {

    public void update(GamePanel gp) {
        Environment currentEnv = gp.getEnvM().getCurrentEnv();
        List<Event> events = currentEnv.getEvents();

        double diceRoll = Math.random();

        for (Event event : events) {
            if (diceRoll <= event.getProbability()) {
                event.execute(gp);
            }
        }
    }
}