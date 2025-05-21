package game.itens.alimentos;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Fruta extends Alimento{
    public Fruta(String nome) {
        super(nome, 1, 1, 10, "Comida", 5);
        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/itens/maca.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
