package game.events;

import game.environments.Environment;
import game.events.eventoDoencaFerimento.SicknessInjuryEvent;
import game.graphics.GamePanel;

import java.util.List;

public class EventManager {

    public void update(GamePanel gp) {
        Environment currentEnv = gp.getEnvM().getCurrentEnv();
        List<Event> events = currentEnv.getEvents();

        double diceRoll = Math.random();

        // this adds new conditions or executes the 1 time only conditions
        for (Event event : events) {
            if (diceRoll <= event.getProbability()) {
                if (event instanceof SicknessInjuryEvent e) {

                    if (!gp.getPlayer().searchCondition(e)) {
                        gp.getPlayer().addCondition(e);
                        event.execute(gp);
                    }
                } else {
                    event.execute(gp);
                }
            }
        }

        // this executes the conditions that are already on the list of conditions
        if (gp.getPlayer().getConditions() != null) {
            for (SicknessInjuryEvent condition : gp.getPlayer().getConditions()) {
                condition.update(gp);
            }
        }
    }
}