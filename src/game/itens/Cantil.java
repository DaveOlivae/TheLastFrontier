package game.itens;

import game.entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Cantil extends Item{
    public Cantil() {
        super("Cantil", 1, 100, false);

        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/canteen.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usar(Player jogador) {

    }
}
