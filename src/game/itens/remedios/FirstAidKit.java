package game.itens.remedios;

import game.entity.Player;
import game.events.eventoDoencaFerimento.Hypotermia;
import game.events.eventoDoencaFerimento.SicknessInjuryEvent;

import java.util.List;

public class FirstAidKit extends Medicine{

    public FirstAidKit() {
        super("aid", 1, 1);

        setImage("/itens/firstaid.png");
    }

    @Override
    public void updateDescription() {
        setDescription("Cures all conditions");
    }

    public void use(Player player) {
        List<SicknessInjuryEvent> conditions = player.getConditions();

        conditions.removeIf(condition -> !(condition instanceof Hypotermia));
    }
}
