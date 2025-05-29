package game.entity;

import game.CombatHandler;
import game.graphics.GamePanel;
import game.input.KeyHandler;
import game.itens.*;

import java.awt.*;
import java.util.List;

public abstract class Player extends Entity {

    public final int screenX;
    public final int screenY;

    private CombatHandler combH;
    private KeyHandler keyH;

    private Inventory inventory;
    private Item equippedItem;

    private int maxHunger = 100;
    private int hunger;

    private int maxThirst = 100;
    private int thirst;

    private int maxEnergy = 100;
    private int energy;

    private int maxSanity = 100;
    private int sanity;


    public Player(String name, int maxLife, int maxHunger, int maxThirst, int maxEnergy, int maxSanity, int maxWeight, GamePanel gp, KeyHandler keyH) {

        super(name, gp, 8, 16, 48, 48, 4, "down");

        /* -------- config for the player collision rectangle --------------- */
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

        /* --------- players starting position -------------- */
        setEnvX(14 * gp.tileSize);
        setEnvY(26 * gp.tileSize);

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2) + gp.hudHeight/2;

        this.keyH = keyH;

        /* ------------ player's attributes ---------------- */
        setMaxLife(maxLife);
        setLife(getMaxLife());

        this.maxHunger = maxHunger;
        this.hunger = 0;

        this.maxThirst = maxThirst;
        this.thirst = 0;

        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;

        this.maxSanity = maxSanity;
        this.sanity = maxSanity;

        this.inventory = new Inventory(maxWeight);

        starterKit();
    }

    public abstract void starterKit();

    public void update() {

        // when there are no keys being pressed the standard sprite is the one in the index 1
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (this.keyH.upPressed) {
                setDirection("up");
            } else if (this.keyH.downPressed) {
                setDirection("down");
            } else if (keyH.rightPressed) {
                setDirection("right");
            } else {
                setDirection("left");
            }

            /* ------------ check tile/object collision -------------- */

            // tile collision
            setCollisionOn(false);
            gp.getcChecker().checkTile(this);

            // object collision
            int objIndex = gp.getcChecker().checkObject(this, true);

            pickupItem(objIndex);

            // check collision with npcs

            int npcIndex = gp.getcChecker().checkEntity(this, gp.getNPCs());

            interactNPC(npcIndex);

            int enemyIndex = gp.getcChecker().checkEntity(this, gp.getEnemies());

            attacked(enemyIndex);

            // if collision is false, player can move
            if (!getCollisionOn()) {
                int newEnvY = getEnvY();
                int newEnvX = getEnvX();
                switch (getDirection()) {
                    case "up":
                        newEnvY -= getSpeed();
                        break;
                    case "down":
                        newEnvY += getSpeed();
                        break;
                    case "right":
                        newEnvX += getSpeed();
                        break;
                    case "left":
                        newEnvX -= getSpeed();
                        break;
                }
                setEnvY(newEnvY);
                setEnvX(newEnvX);
            }

            /* --------- sprite changer ----------- */

            // every time the sprite counter is over 5, the sprite num will changes by one, the sprite num is the index
            // for the sprites array

            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 5) {
                setSpriteNum(getSpriteNum() + 1);
                setSpriteCounter(0);
            }

            if (getSpriteNum() > 2) {
                setSpriteNum(0);
            }
        } else {
            setSpriteNum(1);
            int npcIndex = gp.getcChecker().checkEntity(this, gp.getNPCs());

            interactNPC(npcIndex);
        }

    }

    public void pickupItem(int index) {
        List<Item> itens = gp.getItens();

        if (index != 999) {
            Item item = itens.get(index);

            if (inventory.addItem(item)) {
                itens.remove(index);
            }
        }
    }

    public void interactNPC(int index) {
        if (index != 999) {
            if (gp.getKeyH().ePressed) {
                gp.gameState = gp.dialogueState;
                gp.getNPC(index).speak();
//                gp.getKeyH().ePressed = false;
            }
        }
    }

    public void attacked(int index) {
        if (index != 999) {
            gp.gameState = gp.combatState;

            combH = new CombatHandler(this, gp.getEnemy(index));
        }
    }

    public void draw(Graphics2D g2) {

        switch (getDirection()) {
            case "up":
                setImage(up[getSpriteNum()]);
                break;
            case "down":
                setImage(down[getSpriteNum()]);
                break;
            case "right":
                setImage(right[getSpriteNum()]);
                break;
            case "left":
                setImage(left[getSpriteNum()]);
                break;
        }

        int x = screenX;
        int y = screenY;

        if (screenX > getEnvX()) {
            x = getEnvX();
        }
        if (screenY > getEnvY() + gp.hudHeight) {
            y = getEnvY() + gp.hudHeight;
        }

        int rightOffset = gp.screenWidth - screenX;

        if (rightOffset > gp.envWidth - getEnvX()) {
            x = gp.screenWidth - (gp.envWidth - getEnvX());
        }

        int bottomOffset = gp.screenHeight - screenY;

        if (bottomOffset > gp.envHeight - getEnvY()) {
            y = gp.screenHeight - (gp.envHeight - getEnvY());
        }

        g2.drawImage(getImage(), x, y, gp.tileSize, gp.tileSize, null);
    }

    public void selectItem() {
        int itemIndex = gp.getUi().getIndexOnSlot();

        if (itemIndex < inventory.getItens().size()) {
            Item item = inventory.getItem(itemIndex);

            equippedItem = item;
        }
    }

    public void equipItem(Item item) {
        equippedItem = item;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public List<Item> itens() {
        return inventory.getItens();
    }

    public void addItem(Item item) {

        // the inventory method returns a boolean, but it is only necessary when checking if the item was added
        inventory.addItem(item);
    }

    public CombatHandler getCombH() {
        return combH;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public int getHunger() {
        return hunger;
    }

    public int getMaxThirst() {
        return maxThirst;
    }

    public int getThirst() {
        return thirst;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxSanity() {
        return maxSanity;
    }

    public int getSanity() {
        return sanity;
    }
}
