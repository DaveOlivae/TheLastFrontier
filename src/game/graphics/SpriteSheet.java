package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

// this class is used to get the frames from the sprite sheet

public class SpriteSheet {
    private BufferedImage spriteSheet;

    public SpriteSheet(String path) {
        try {
            spriteSheet = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getFrame(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    // im creating this get height and width methods to make it easier to load the tile set

    public int getHeight() {
        return this.spriteSheet.getHeight();
    }

    public int getWidth() {
        return this.spriteSheet.getWidth();
    }
}
