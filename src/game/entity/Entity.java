package game.entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage[] up = new BufferedImage[12];
    public BufferedImage[] down = new BufferedImage[12];
    public BufferedImage[] left = new BufferedImage[12];
    public BufferedImage[] right = new BufferedImage[12];
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;
}
