package game.entity;

import game.logic.CollisionChecker;
import game.graphics.GamePanel;
import game.graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Entity {

    public GamePanel gp;

    private int envX, envY;  // these variables relate to the position of the player on the environment
    private int speed;

    public BufferedImage[] up = new BufferedImage[3];
    public BufferedImage[] down = new BufferedImage[3];
    public BufferedImage[] left = new BufferedImage[3];
    public BufferedImage[] right = new BufferedImage[3];

    private String direction;

    private int spriteCounter = 0;
    private int spriteNum = 0;

    private Rectangle solidArea;  // this is the std rectangle for entities
    // if a subclass needs a different solid area, it can simply override it

    private int solidAreaDefaultX, solidAreaDefaultY;

    private int actionLockCounter;
    private boolean collisionOn = false;
    private BufferedImage image;

    private List<String> dialogues;
    private int dialogueIndex;

    private int maxLife;
    private int life;
    private String name;

    public Entity(String name, GamePanel gp, int x, int y, int width, int height, int speed, String direction) {
        this.name = name;
        this.gp = gp;
        this.solidArea = new Rectangle();
        this.solidArea.x = x;
        this.solidArea.y = y;
        this.solidArea.width = width;
        this.solidArea.height = height;
        this.speed = speed;
        this.direction = direction;

        this.dialogues = new ArrayList<>();
    }

    public void update() {
        CollisionChecker cChecker = gp.getcChecker();

        setAction();
        collisionOn = false;
        cChecker.checkTile(this);
        cChecker.checkObject(this, false);
        cChecker.checkBorders(this);
        cChecker.checkPlayer(this);

        // if collision is false, entity can move
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    envY -= speed;
                    break;
                case "down":
                    envY += speed;
                    break;
                case "right":
                    envX += speed;
                    break;
                case "left":
                    envX -= speed;
                    break;
            }
        }

        /* --------- sprite changer ----------- */

        // every time the sprite counter is over 5, the sprite num will changes by one, the sprite num is the index
        // for the sprites array
        ++spriteCounter;
        if (spriteCounter > 7) {
            ++spriteNum;
            spriteCounter = 0;
        }

        if (spriteNum > 2) {
            spriteNum = 0;
        }
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int num = random.nextInt(100) + 1;

            if (num <= 25) {
                direction = "up";
            }
            if (num > 25 && num <= 50) {
                direction = "down";
            }
            if (num > 50 && num <= 75) {
                direction = "left";
            }
            if (num > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }

    public void loadImage(String path) {
        // this method loads/crops the sprites of the entity

        SpriteSheet spriteSheet = new SpriteSheet(path);

        for (int i = 0; i < 3; i++) {
            up[i] = spriteSheet.getFrame(i * 16, 48, 16, 16);
        }

        for (int i = 0; i < 3; i++) {
            down[i] = spriteSheet.getFrame(i * 16, 0, 16, 16);
        }

        for (int i = 0; i < 3; i++) {
            right[i] = spriteSheet.getFrame(i * 16, 32, 16, 16);
        }

        for (int i = 0; i < 3; i++) {
            left[i] = spriteSheet.getFrame(i * 16, 16, 16, 16);
        }
    }

    public void speak() {
        if (dialogueIndex >= dialogues.size()) {
            dialogueIndex = 0;
        }

        gp.getUi().setCurrentDialogue(dialogues.get(dialogueIndex));
        dialogueIndex++;

        switch (gp.getPlayer().getDirection()) {
            case "up":
                setDirection("down");
                break;
            case "down":
                setDirection("up");
                break;
            case "left":
                setDirection("right");
                break;
            case "right":
                setDirection("left");
                break;
        }
    }

    public void draw(Graphics2D g2) {
        Player player = gp.getPlayer();
        int envX = player.getEnvX();
        int envY = player.getEnvY();

        switch (direction) {
            case "up":
                image = up[spriteNum];
                break;
            case "down":
                image = down[spriteNum];
                break;
            case "right":
                image = right[spriteNum];
                break;
            case "left":
                image = left[spriteNum];
                break;
        }

        int screenX = this.envX - envX + player.screenX;
        int screenY = this.envY - envY + player.screenY;

        if (player.screenX > envX) {
            screenX = this.envX;
        }
        if (player.screenY > envY + gp.hudHeight) {
            screenY = this.envY + gp.hudHeight;
        }
        int rightOffset = gp.screenWidth - player.screenX;
        if (rightOffset > gp.envWidth - envX) {
            screenX = gp.screenWidth - (gp.envWidth - this.envX);
        }
        int bottomOffset = gp.screenHeight - player.screenY;
        if (bottomOffset > gp.envHeight - envY) {
            screenY = gp.screenHeight - (gp.envHeight - this.envY);
        }

        if (this.envX + gp.tileSize > envX - player.screenX &&
                this.envX - gp.tileSize < envX + player.screenX &&
                this.envY + gp.tileSize > envY - player.screenY &&
                this.envY - gp.tileSize < envY + player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        } else if (player.screenX > envX ||
                player.screenY > envY ||
                rightOffset > gp.envWidth - envX ||
                bottomOffset > gp.envHeight - envY) {

            // this condition deals with the rendering of tiles when the player is in the border area
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void setForFight(Graphics2D g2, int screenX, int screenY, String direction) {
        setEnvX(screenX);
        setEnvY(screenY);
        setDirection(direction);
        g2.drawImage(image,screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void damage(int points) {
        if ((life - points) <= 0) {
            life = 0;
        } else {
            life -= points;
        }
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        if (life <= 100) {
            this.life = life;
        }
    }

    public void setDialogue(String dialogue) {
        this.dialogues.add(dialogue);
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultX() {
        return this.solidAreaDefaultX;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public int getSolidAreaDefaultY() {
        return this.solidAreaDefaultY;
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Rectangle getSolidArea() {
        return this.solidArea;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public boolean getCollisionOn() {
        return this.collisionOn;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getEnvX() {
        return this.envX;
    }

    public void setEnvX(int envX) {
        this.envX = envX;
    }

    public int getEnvY() {
        return this.envY;
    }

    public void setEnvY(int envY) {
        this.envY = envY;
    }

    public String getName() {
        return name;
    }
}
