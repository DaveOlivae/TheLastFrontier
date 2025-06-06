package game.itens.food;

import game.entity.Player;
import game.itens.Consumable;

public class Coffee extends Consumable {
    private int energyRestored = 10;

    public Coffee() {
        super("Coffee", "Coffee", 10);

        setImage("/itens/coffee.png");
    }

    @Override
    public void use(Player player) {
        if (player.getEnergy() < 100) {
            if (player.getEnergy() + energyRestored > 100) {
                player.setEnergy(100);
            } else {
                player.setEnergy(player.getEnergy() + energyRestored);
            }
        }
    }

    @Override
    public void updateDescription() {
        setDescription("Restores " + energyRestored + " of energy");
    }
}
