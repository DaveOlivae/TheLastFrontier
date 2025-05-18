package game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int envX, envY;  // these variables relate to the position of the player on the environment
    public int speed;

    public BufferedImage[] up = new BufferedImage[3];
    public BufferedImage[] down = new BufferedImage[3];
    public BufferedImage[] left = new BufferedImage[3];
    public BufferedImage[] right = new BufferedImage[3];
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int getEnvX() {
        return this.envX;
    }

    public int getEnvY() {
        return this.envY;
    }

    public void setEnvX(int envX) {
        this.envX = envX;
    }

    public void setEnvY(int envY) {
        this.envY = envY;
    }
}
