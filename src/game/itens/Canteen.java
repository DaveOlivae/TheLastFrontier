package game.itens;

import game.entity.Player;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Canteen extends Item{
    public Canteen() {
        super("item","Canteen", 1, 100, false);

        setImage("/itens/canteen.png");
    }
}
