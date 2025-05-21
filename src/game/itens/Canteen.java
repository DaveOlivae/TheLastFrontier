package game.itens;

import game.entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Canteen extends Item{
    public Canteen() {
        super("Canteen", 1, 100, false);

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/canteen.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usar(Player jogador) {

    }
}
