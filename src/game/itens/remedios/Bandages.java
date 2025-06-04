package game.itens.remedios;

import game.entity.Player;

public class Bandages extends Medicine {
    private int healingPoints = 20;

    public Bandages() {
        super("aid", 1, 1);


    }

    @Override
    public void updateDescription() {
        setDescription("Heals 20 hp");
    }

    public void use(Player player) {
        player.setLife(player.getLife() + healingPoints);
    }
}
