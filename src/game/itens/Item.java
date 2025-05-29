package game.itens;

import game.entity.Player;
import game.graphics.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Item {

    private BufferedImage image;
    private String name;
    public boolean collision = false;
    public int envX, envY;

    // these values for the rectangle are set because so the rectangle will be exaclty 1 tile
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDefaultX, solidAreaDefaultY;

    private float weight;
    private int durability;
    private boolean equipable;
    private String description;
    private String type;

    public Item(String type, String name, float weight, int durability, boolean equipable) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.durability = durability;
        this.equipable = equipable;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        Player player = gp.getPlayer();
        int playerEnvX = player.getEnvX();
        int playerEnvY = player.getEnvY();

        // this method is similar to the draw method of the tile manager
        // it has to deal with the absolute and relative positions of the object, and take care of them when the player
        // is at the borders of the environment

        int screenX = envX - playerEnvX + player.screenX;
        int screenY = envY - playerEnvY + player.screenY;

        if (player.screenX > playerEnvX) {
            screenX = envX;
        }
        if (player.screenY > playerEnvY + gp.hudHeight) {
            screenY = envY + gp.hudHeight;
        }
        int rightOffset = gp.screenWidth - player.screenX;
        if (rightOffset > gp.envWidth - playerEnvX) {
            screenX = gp.screenWidth - (gp.envWidth - envX);
        }
        int bottomOffset = gp.screenHeight - player.screenY;
        if (bottomOffset > gp.envHeight - playerEnvY) {
            screenY = gp.screenHeight - (gp.envHeight - envY);
        }

        if (envX + gp.tileSize > playerEnvX - player.screenX &&
                envX - gp.tileSize < playerEnvX + player.screenX &&
                envY + gp.tileSize > playerEnvY - player.screenY &&
                envY - gp.tileSize < playerEnvY + player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        } else if (player.screenX > playerEnvX ||
                player.screenY > playerEnvY ||
                rightOffset > gp.envWidth - playerEnvX ||
                bottomOffset > gp.envHeight - playerEnvY) {

            // this condition deals with the rendering of tiles when the player is in the border area
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void setImage(String path) {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.image = image;
    }

    public String getType() {
        return type;
    }

    public int getDurability() {
        return durability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return this.name;
    }

    public float getWeight() {
        return weight;
    }
}
