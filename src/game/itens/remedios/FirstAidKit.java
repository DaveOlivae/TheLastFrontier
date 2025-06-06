package game.itens.remedios;

import game.entity.Player;
import game.events.eventoDoencaFerimento.Hypotermia;
import game.events.eventoDoencaFerimento.SicknessInjuryEvent;

import java.util.List;

public class FirstAidKit extends Medicine{

    public FirstAidKit() {
        super("firstaidkit", 1);

        setImage("/itens/firstaid.png");
    }

    @Override
    public void updateDescription() {
        setDescription("Cures all conditions");
    }

    @Override
    public void use(Player player) {
        List<SicknessInjuryEvent> conditions = player.getConditions();

        conditions.removeIf(condition -> !(condition instanceof Hypotermia));

        setAmount(getAmount() - 1);
    }
}
