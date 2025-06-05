package game.entity;

import game.events.eventoDoencaFerimento.Hypotermia;
import game.events.eventoDoencaFerimento.SicknessInjuryEvent;
import game.itens.food.Coffee;
import game.itens.weapons.Ammo;
import game.itens.weapons.Firearm;
import game.logic.CombatHandler;
import game.graphics.GamePanel;
import game.input.KeyHandler;
import game.itens.*;
import game.itens.food.Food;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Entity {

    public final int screenX;
    public final int screenY;

    private CombatHandler combH;
    private KeyHandler keyH;

    private Inventory inventory;
    private Item equippedItem;

    private List<SicknessInjuryEvent> conditions;
    private List<Item> currentLoot;

    private int currentEnemy;

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

        this.conditions = new ArrayList<>();
        this.currentLoot = new ArrayList<>();

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

            // interact with special tiles
            int resourceIndex = gp.getcChecker().collectResource(this);
            collectResource(resourceIndex);

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

            // interact with special tiles
            int resourceIndex = gp.getcChecker().collectResource(this);
            collectResource(resourceIndex);
        }

        /* ---------------- UPDATE PLAYER STATS -------------------- */

        if (getLife() <= 0) {
            gp.gameState = gp.gameOverState;
        }

        if ((gp.getClock().getTime() % 200) == 0) {
            hunger += 1;
            thirst += 3;
        }

        if (gp.getClock().getTime() > 18000 & gp.getClock().getTime() % 100 == 0) {
            energy -= 1;
        }

        if (hunger >= 100) {
            gp.getUi().setCurrentDialogue("You died of starvation");
            gp.gameState = gp.gameOverState;
        }

        if (thirst >= 100) {
            gp.getUi().setCurrentDialogue("You died of dehydration");
            gp.gameState = gp.gameOverState;
        }

        if (energy <= 0) {
            gp.getUi().setCurrentDialogue("You died of fatigue");
            gp.gameState = gp.gameOverState;
        }

        if (sanity <= 0) {
            gp.getUi().setCurrentDialogue("You went insane");
            gp.gameState = gp.gameOverState;
        }

    }

    public void pickupItem(int index) {
        List<Item> itens = gp.getItens();

        if (index != 999) {
            Item item = itens.get(index);

            if (item instanceof Ammo ammo) {
                Ammo playerAmmo = inventory.getAmmo(ammo.getName());

                if (playerAmmo != null) {
                    playerAmmo.setQuantity(playerAmmo.getQuantity() + ammo.getQuantity());
                    itens.remove(index);
                } else {
                    if (inventory.addItem(ammo)) {
                        itens.remove(index);
                    }
                }

            } else if (item instanceof Loot loot) {
                gp.gameState = gp.lootState;
                currentLoot = loot.getLoot();

            } else if (inventory.addItem(item)) {
                itens.remove(index);
            }

        }
    }

    public void collectResource(int index) {
        if (gp.getKeyH().ePressed && index != 999) {
            gp.gameState = gp.dialogueState;

            if (index == 60 || index == 61 || index == 158) {
                // player collects water
                Canteen canteen = getCanteen();

                if (canteen != null) {
                    canteen.addWater();
                    gp.getUi().setCurrentDialogue("You've collected water");
                } else {
                    gp.getUi().setCurrentDialogue("You don't have a canteen");
                }
            }

            if (index == 210 || index == 211 || index == 212 || index == 213) {
                conditions.removeIf(event -> event instanceof Hypotermia);

                gp.getUi().setCurrentDialogue("You've warmed yourself.\n");
            }

            if (index == 214 || index == 215 || index == 216 || index == 217) {
                sleep();
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
            currentEnemy = index;
            gp.gameState = gp.combatState;

            combH = new CombatHandler(this, gp.getEnemy(index), gp);
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

    public void sleep() {
        if (gp.getClock().getTime() >= 18000) {
            gp.getClock().setTime(0);
            gp.getClock().dayPassed();
            gp.getUi().setCurrentDialogue("You wake up feeling well\nrested.");

            energy = maxEnergy;
            setLife(getMaxLife());
        } else {
            gp.getUi().setCurrentDialogue("You can only sleep\nat night.");
        }
    }

    public void selectLoot() {
        int itemIndex = gp.getUi().getIndexOnSlot();

        if (itemIndex < currentLoot.size()) {
            Item item = currentLoot.get(itemIndex);

            if (item instanceof Ammo ammo) {
                Ammo playerAmmo = inventory.getAmmo(ammo.getName());

                if (playerAmmo != null) {
                    playerAmmo.setQuantity(playerAmmo.getQuantity() + ammo.getQuantity());
                    currentLoot.remove(itemIndex);
                } else {
                    if (inventory.addItem(ammo)) {
                        currentLoot.remove(itemIndex);
                    }
                }
            } else if (inventory.addItem(item)) {
                currentLoot.remove(itemIndex);
            }
        }

        if (currentLoot.isEmpty()) {
            gp.gameState = gp.playState;

            gp.getItens().removeIf(item -> item instanceof Loot loot && loot.getLoot().isEmpty());
        }
    }

    public void selectItem() {
        int itemIndex = gp.getUi().getIndexOnSlot();

        if (itemIndex < inventory.getItens().size()) {
            Item item = inventory.getItem(itemIndex);

            if (item.getType().equals("weapon")) {
                equippedItem = item;

                // this handles showing the message when the player changes equipment on the item option
                if (gp.gameState == gp.combatState) {
                    gp.getCombatHandler().setTurnMessage("You equiped " + item.getName());
                }
            } else if (item.getType().equals("food")) {
                Food food = (Food) item;

                if (food instanceof Coffee coffee) {
                    setEnergy(energy - coffee.getEnergyRestored());
                } else {
                    eat(food);
                }

                food.setAmount(food.getAmount() - 1);

                if (food.getAmount() <= 0) {
                    itens().remove(food);
                }
            } else if (item instanceof Canteen canteen) {
                canteen.drinkWater(this);
            }
        }
    }

    public void removeItem() {
        int itemIndex = gp.getUi().getIndexOnSlot();

        if (itemIndex < inventory.getItens().size()) {
            inventory.removeItem(itemIndex);
        }
    }

    public void equipItem(Item item) {
        equippedItem = item;
    }

    public void eat(Food food) {
        int hungerPoints = food.getHungerPoints();

        hunger -= hungerPoints;
        if (hunger <= 0) {
            hunger = 0;
        }
    }

    public void drink(int thirstPoints) {
        thirst -= thirstPoints;
        if (thirst <= 0) {
            thirst = 0;
        }
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public List<Item> itens() {
        return inventory.getItens();
    }

    public Ammo getPlayerAmmo(String ammoType) {
        return inventory.getAmmo(ammoType);
    }

    private Canteen getCanteen() {
        Canteen canteen;

        for (Item item : itens()) {
            if (item instanceof Canteen) {
                canteen = (Canteen) item;
                return canteen;
            }
        }

        return null;
    }

    public void reloadGun(Firearm gun) {
        inventory.reloadFirearm(gun);
    }

    public void addItem(Item item) {

        // the inventory method returns a boolean, but it is only necessary when checking if the item was added
        inventory.addItem(item);
    }

    public void subtractUseItem() {
        equippedItem.subtractUse();
    }

    public CombatHandler getCombH() {
        return combH;
    }

    public int getCurrentEnemy() {
        return currentEnemy;
    }

    public double getWeight() {
        return inventory.getWeight();
    }

    public List<SicknessInjuryEvent> getConditions() {
        return conditions;
    }

    public boolean searchCondition(SicknessInjuryEvent e) {
        for (SicknessInjuryEvent condition : conditions) {
            if (condition.getName().equals(e.getName())) {
                return true;
            }
        }

        return false;
    }

    public List<Item> getCurrentLoot() {
        return currentLoot;
    }

    public void addCondition(SicknessInjuryEvent condition) {
        conditions.add(condition);
    }

    public double getMaxWeight() {
        return inventory.getMaxWeight();
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public int getHunger() {
        return hunger;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
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

    public void setEnergy(int energy) {
        this.energy = energy;
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

    public void sanityPoints(int points) {
        sanity -= points;
        if (sanity <= 0) {
            sanity = 0;
        }
    }
}
