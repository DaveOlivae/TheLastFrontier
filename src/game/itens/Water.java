package game.itens;

import game.entity.Player;
import game.events.eventoDoencaFerimento.Dehydration;

public class Water extends Consumable{
    private int thirstPoints = 30;

    public Water() {
        super("water", "water", 1);
        setImage("/itens/water.png");
    }

    @Override
    public void updateDescription() {
        setDescription("Heals " + thirstPoints + " thirst points");
    }

    @Override
    public void use(Player player) {
        player.drink(thirstPoints);

        if (player.searchCondition("dehydration")) {
            player.removeCondition("dehydration");
        }
    }
}
