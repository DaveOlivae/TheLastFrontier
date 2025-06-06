package game.itens.remedios;

import game.entity.Player;

public class Bandages extends Medicine {
    private int healingPoints = 40;

    public Bandages() {
        super("bandages", 1);

        setImage("/itens/bandages.png");
    }

    @Override
    public void updateDescription() {
        setDescription("Heals " + healingPoints + " hp");
    }

    @Override
    public void use(Player player) {
        player.setLife(player.getLife() + healingPoints);

        setAmount(getAmount() - 1);
    }
}
