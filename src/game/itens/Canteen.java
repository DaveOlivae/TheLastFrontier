package game.itens;

import game.entity.Player;

public class Canteen extends Item{
    private final int thirstPoints = 20;
    private final int maxCapacity = 5;
    private int volume;

    public Canteen() {
        super("consumable", "canteen", 1, 999, false);

        volume = maxCapacity;

        setImage("/itens/canteen.png");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        setDescription("Stores water\nQuantity: " + volume + "/" + maxCapacity);
    }

    public void drinkWater(Player player) {
        if (volume >= 1) {
            player.drink(thirstPoints);
            volume--;
        }
    }

    public void addWater() {
        if (volume < maxCapacity) {
            volume++;
        }
    }
}
