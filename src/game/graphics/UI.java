package game.graphics;

import game.events.eventoDoencaFerimento.SicknessInjuryEvent;
import game.logic.CombatHandler;
import game.environments.Environment;
import game.environments.EnvironmentManager;
import game.entity.Player;
import game.itens.Item;
import game.itens.weapons.Firearm;
import game.itens.weapons.Knife;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class UI {
    private GamePanel gp;
    private Font font;
    private Graphics2D g2;
    private String currentDialogue;
    private int commandNum = 0;
    private int titleScreenState = 0;
    private int inventoryScreenState = 0;
    private int slotCol, slotRow;
    private boolean slotNotEmpty;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/PixelOperator.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));

        if (gp.gameState == gp.playState) {
            drawStats();
        }
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.pauseState) {
            drawStats();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawStats();
            drawDialogueScreen();
        }
        if (gp.gameState == gp.combatState) {
            drawCombatScreen();
        }
        if (gp.gameState == gp.inventoryState) {
            drawStats();
            drawInventory();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        if (gp.gameState == gp.lootState) {
            drawStats();
            drawLootBox();
        }

    }

    public void drawLootBox() {
        BufferedImage image;
        String text;

        Player player = gp.getPlayer();
        List<Item> loot = player.getCurrentLoot();

        int x = gp.tileSize*8;
        int y = gp.tileSize*3;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/inventory_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, 480, 392, null);

        /* -------------- draw itens ------------------ */

        int startX = x + 20;
        int startY = y + 20;
        int slotX = startX + 12;
        int slotY = startY + 12;

        for (int i = 0; i < loot.size(); i++) {
            g2.drawImage(loot.get(i).getImage(), slotX, slotY, gp.tileSize, gp.tileSize, null);

            // DISPLAY AMOUNT

            if (loot.get(i).getAmount() > 1) {
                String s = "" + loot.get(i).getAmount();
                int amountX = slotX + gp.tileSize;
                int amountY = slotY + gp.tileSize;

                drawText(s, amountX, amountY, 32);
            }

            slotX += (gp.tileSize + 24);

            if (i == 4 || i == 9 || i == 14 || i == 19) {
                slotX = startX + 12;
                slotY += (gp.tileSize + 24);
            }
        }

        /* ------------- cursor --------------- */

        int selectionColSide = 88;
        int selX = startX + selectionColSide*slotCol;
        int selY = startY + selectionColSide*slotRow;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/selection_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, selX, selY, selectionColSide, selectionColSide, null);

        /* ---------------- description box ------------------- */

        y += 392;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/description_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, 480, 248, null);

        x += gp.tileSize/2;
        y += 55;

        int itemIndex = getIndexOnSlot();

        slotNotEmpty = itemIndex < loot.size();

        if (slotNotEmpty) {

            loot.get(itemIndex).updateDescription();

            text = loot.get(itemIndex).getDescription();

            if (loot.get(itemIndex) instanceof Firearm gun) {
                // all of this is to deal with the ammo

                String ammoType = gun.getFirearmType();

                if (player.getPlayerAmmo(ammoType) == null) {
                    text = loot.get(itemIndex).getDescription() + "0";
                } else {
                    int quantity = player.getPlayerAmmo(ammoType).getQuantity();
                    text = loot.get(itemIndex).getDescription() + quantity;
                }
            }

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45));

            g2.setColor(Color.white);

            // this is made so that the description of the itens fits in the description box
            int counter = 0;
            for (String line : text.split("\n")) {
                g2.drawString(line, x, y);
                y += 40;
                counter++;
                if (counter == 5) {
                    y -= 200;
                    x += gp.tileSize*5;
                    counter = 0;
                }
            }
        }

        /* --------------- selecting an item ----------------------- */


        if (itemIndex < loot.size() && inventoryScreenState == 1) {

            int boxX = selX + gp.tileSize / 2;
            int boxY = selY + gp.tileSize / 2;

            drawBox("/hud/options_box.png", boxX, boxY, gp.tileSize * 3, gp.tileSize * 3);

            int stringX = boxX + gp.tileSize / 2 + gp.tileSize / 6;
            int stringY = boxY + 60;

            text = "Get";
            drawText(text, stringX, stringY, 45);
            if (commandNum == 0) {
                g2.drawString(">", stringX - gp.tileSize / 4, stringY);
            }

            stringY += 50;
            text = "Cancel";
            drawText(text, stringX, stringY, 45);
            if (commandNum == 2) {
                g2.drawString(">", stringX - gp.tileSize / 4, stringY);
            }
        }
    }

    private void drawGameOverScreen() {
        Color c = new Color(0, 0, 0);

        g2.setColor(c);

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);

        String text = "Game Over";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;
        drawTextBold(text, x, y, 100);

        text = currentDialogue;
        x = getXforCenteredText(text);
        y += gp.tileSize*3;
        drawText(text, x, y, 80);

        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*3;
        drawText(text, x, y, 80);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        drawText(text, x, y, 80);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    private void drawAttributesBox(int x, int y, Player player) {
        int space = 45;
        int textSize = 40;
        int width = gp.tileSize*5;
        int height = gp.tileSize*10;

        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/stats_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, width, height, null);

        x += gp.tileSize/3;
        y += gp.tileSize;
        String text = "HP: " + player.getLife() + "/" + player.getMaxLife();
        drawTextBold(text, x, y, textSize);

        y += space;
        text = "Hunger: " + player.getHunger() + "/" + player.getMaxHunger();
        drawTextBold(text, x, y, textSize);

        y += space;
        text = "Thirst: " + player.getThirst() + "/" + player.getMaxThirst();
        drawTextBold(text, x, y, textSize);

        y += space;
        text = "Energy: " + player.getEnergy() + "/" + player.getMaxEnergy();
        drawTextBold(text, x, y, textSize);

        y += space;
        text = "Sanity: " + player.getSanity() + "/" + player.getMaxSanity();
        drawTextBold(text, x, y, textSize);

        y += space;
        text =  "Weight: " + String.format("%.1f", player.getWeight()) + "/" + String.format("%.0f", player.getMaxWeight());
        drawTextBold(text, x, y, textSize);

        y += space;
        text = "Equipped\nItem:";
        drawTextBold(text, x, y, textSize);

        image = player.getEquippedItem().getImage();
        g2.drawImage(image, x + gp.tileSize*3, y - 25, gp.tileSize, gp.tileSize, null);
    }

    private void drawItemsBox(int x, int y, Player player) {
        BufferedImage image;
        String text;

        List<Item> itens = player.itens();

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/inventory_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, 480, 392, null);

        /* -------------- draw itens ------------------ */

        int startX = x + 20;
        int startY = y + 20;
        int slotX = startX + 12;
        int slotY = startY + 12;

        for (int i = 0; i < itens.size(); i++) {
            g2.drawImage(itens.get(i).getImage(), slotX, slotY, gp.tileSize, gp.tileSize, null);

            // DISPLAY AMOUNT

            if (itens.get(i).getAmount() > 1) {
                String s = "" + itens.get(i).getAmount();
                int amountX = slotX + gp.tileSize;
                int amountY = slotY + gp.tileSize;

                drawText(s, amountX, amountY, 32);
            }

            slotX += (gp.tileSize + 24);

            if (i == 4 || i == 9 || i == 14 || i == 19) {
                slotX = startX + 12;
                slotY += (gp.tileSize + 24);
            }
        }

        /* ------------- cursor --------------- */

        int selectionColSide = 88;
        int selX = startX + selectionColSide*slotCol;
        int selY = startY + selectionColSide*slotRow;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/selection_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, selX, selY, selectionColSide, selectionColSide, null);

        /* ---------------- description box ------------------- */

        y += 392;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/description_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, 480, 248, null);

        x += gp.tileSize/2;
        y += 55;

        int itemIndex = getIndexOnSlot();

        slotNotEmpty = itemIndex < itens.size();

        if (slotNotEmpty) {

            itens.get(itemIndex).updateDescription();

            text = itens.get(itemIndex).getDescription();

            if (itens.get(itemIndex) instanceof Firearm gun) {
                // all of this is to deal with the ammo

                String ammoType = gun.getFirearmType();

                if (player.getPlayerAmmo(ammoType) == null) {
                    text = itens.get(itemIndex).getDescription() + "0";
                } else {
                    int quantity = player.getPlayerAmmo(ammoType).getQuantity();
                    text = itens.get(itemIndex).getDescription() + quantity;
                }
            }

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 45));

            g2.setColor(Color.white);

            // this is made so that the description of the itens fits in the description box
            int counter = 0;
            for (String line : text.split("\n")) {
                g2.drawString(line, x, y);
                y += 40;
                counter++;
                if (counter == 5) {
                    y -= 200;
                    x += gp.tileSize*5;
                    counter = 0;
                }
            }
        }

        /* --------------- selecting an item ----------------------- */


        if (itemIndex < itens.size()) {

            String itemType = itens.get(itemIndex).getType();

            if (inventoryScreenState == 1) {

                if (itens.get(itemIndex).equals(gp.getPlayer().getEquippedItem())) {
                    inventoryScreenState = 4;
                } else if (itemType.equals("weapon") || itemType.equals("food") || itemType.equals("consumable") || itemType.equals("water") || itemType.equals("Coffee")) {
                    inventoryScreenState = 2;
                } else {
                    inventoryScreenState = 3;
                }
            }

            if (inventoryScreenState == 2) {
                int boxX = selX + gp.tileSize / 2;
                int boxY = selY + gp.tileSize / 2;

                drawBox("/hud/options_box.png", boxX, boxY, gp.tileSize * 3, gp.tileSize * 3);

                int stringX = boxX + gp.tileSize / 2 + gp.tileSize / 6;
                int stringY = boxY + 60;

                if (itemType.equals("food")) {
                    text = "Eat";
                } else if (itemType.equals("weapon")) {
                    text = "Equip";
                } else {
                    text = "Use";
                }
                drawText(text, stringX, stringY, 45);
                if (commandNum == 0) {
                    g2.drawString(">", stringX - gp.tileSize / 4, stringY);
                }

                stringY += 50;
                text = "Remove";
                drawText(text, stringX, stringY, 45);
                if (commandNum == 1) {
                    g2.drawString(">", stringX - gp.tileSize / 4, stringY);
                }

                stringY += 50;
                text = "Cancel";
                drawText(text, stringX, stringY, 45);
                if (commandNum == 2) {
                    g2.drawString(">", stringX - gp.tileSize / 4, stringY);
                }
            }

            if (inventoryScreenState == 3) {
                int boxX = selX + gp.tileSize / 2;
                int boxY = selY + gp.tileSize / 2;

                drawBox("/hud/options_box.png", boxX, boxY, gp.tileSize * 3, gp.tileSize * 3);

                int stringX = boxX + gp.tileSize / 2 + gp.tileSize / 6;
                int stringY = boxY + 60;

                text = "Remove";
                drawText(text, stringX, stringY, 45);
                if (commandNum == 0) {
                    g2.drawString(">", stringX - gp.tileSize / 4, stringY);
                }

                stringY += 50;
                text = "Cancel";
                drawText(text, stringX, stringY, 45);
                if (commandNum == 1) {
                    g2.drawString(">", stringX - gp.tileSize / 4, stringY);
                }
            }

            if (inventoryScreenState == 4) {
                int boxX = selX + gp.tileSize / 2;
                int boxY = selY + gp.tileSize / 2;

                drawBox("/hud/options_box.png", boxX, boxY, gp.tileSize * 3, gp.tileSize * 3);

                int stringX = boxX + gp.tileSize / 2 + gp.tileSize / 6;
                int stringY = boxY + 60;

                text = "Cancel";
                drawText(text, stringX, stringY, 45);
                if (commandNum == 0) {
                    g2.drawString(">", stringX - gp.tileSize / 4, stringY);
                }
            }
        }
    }

    private void drawInventory() {
         /* i decided to separate the inventory in 2 boxes, the attributes box and the items box (with the item
         * description box) i did this because im gonna use the items box elsewhere, and i dont need the attributes box
         * everytime, only when the player is check its inventory
         * */

        int x = gp.tileSize;
        int y = gp.tileSize * 3;

        Player player = gp.getPlayer();

        /* ------------- attributes box --------------- */

        drawAttributesBox(x, y, player);

        /* ------------- inventory box --------------- */

        x = gp.tileSize*8;

        drawItemsBox(x, y, player);
    }

    private void drawCombatScreen() {
        // the battle map will differ, depending on the environment the player is at

        EnvironmentManager envM = gp.getEnvM();
        Environment currentEnv = envM.getCurrentEnv();
        CombatHandler combH = gp.getCombatHandler();

        BufferedImage image;

        String message = combH.getTurnMessage();

        String path = "/hud/battle_mat_forest.png";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);

        // this sets the position of the boxes in the window

        /* ---------- player stats ----------- */
        int x = gp.tileSize*2;
        int y = gp.tileSize;
        String text = combH.getPlayer().getName();

        // player name
        drawTextBold(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        x = gp.tileSize*2 + gp.tileSize/2;
        y = gp.tileSize*2 + gp.tileSize/4;

        // life bar
        drawPlayerLife(x, y);

        // life number
        int maxLife = combH.getPlayer().getMaxLife();
        int life = combH.getPlayer().getLife();

        text = maxLife + "/" + life;

        drawTextBold(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        /* ---------- enemy stats ------------- */
        x = gp.tileSize*9;
        y = gp.tileSize;
        text = combH.getTarget().getName();

        // enemy name
        drawTextBold(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        x += gp.tileSize/2;
        y = gp.tileSize*2 + gp.tileSize/4;

        // enemy life
        drawEnemyLife(x, y);

        // life number
        maxLife = combH.getTarget().getMaxLife();
        life = combH.getTarget().getLife();

        text = maxLife + "/" + life;

        drawTextBold(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        x = gp.tileSize/2;
        y = (gp.tileSize*9) + gp.tileSize/8;

        /* ------------ options box -------------- */
        if (combH.getCombatScreenState() == 0) {

            // status message at the beginning of the turn "A raider appeared"

            drawTextBold(message, x + gp.tileSize/2, y + gp.tileSize, 45);

            x = (gp.tileSize * 7);

            x += gp.tileSize;
            y += gp.tileSize;
            text = "What do you wish to do?";
            drawTextBold(text, x, y, 45);

            x += gp.tileSize;
            y += gp.tileSize;
            text = "Attack";
            drawTextBold(text, x, y, 45);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

            text = "Run";
            drawTextBold(text, x + 4 * gp.tileSize, y, 45);
            if (commandNum == 2) {
                g2.drawString(">", x + 3 * gp.tileSize + gp.tileSize / 2, y);
            }

            y += gp.tileSize;
            text = "Item";
            drawTextBold(text, x, y, 45);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }
        } else if (combH.getCombatScreenState() == 1) {
            Item weapon = gp.getPlayer().getEquippedItem();

            if (weapon instanceof Firearm gun) {
                x = (gp.tileSize * 7);

                x += gp.tileSize;
                y += gp.tileSize;
                text = "What do you wish to do?";
                drawTextBold(text, x, y, 45);

                x += gp.tileSize/2;
                y += gp.tileSize;

                if (gun.getLoad() == 0) {
                    text = "Reload";
                } else {
                    text = "Shoot";
                }

                drawTextBold(text, x, y, 45);
                if (commandNum == 0) {
                    g2.drawString(">", x - gp.tileSize / 2, y);
                }

                g2.drawImage(gun.getImage(), x + gp.tileSize*5, y - gp.tileSize/2, gp.tileSize, gp.tileSize, null);

                text = "Damage: " + gun.getDamage() + " pts";
                drawText(text, x, y+gp.tileSize/2, 40);

                int ammoQuantity;
                if (!(gp.getPlayer().getPlayerAmmo(gun.getFirearmType()) == null)) {
                    ammoQuantity = gp.getPlayer().getPlayerAmmo(gun.getFirearmType()).getQuantity();
                } else {
                    ammoQuantity = 0;
                }

                text = gun.getLoad() + "/" + ammoQuantity;
                drawText(text, x + gp.tileSize*5, y+gp.tileSize, 40);

                y += gp.tileSize + gp.tileSize/4;
                text = "Back";
                drawTextBold(text, x, y+gp.tileSize/4, 45);
                if (commandNum == 1) {
                    g2.drawString(">", x - gp.tileSize / 2, y+gp.tileSize/4);
                }
            } else if (weapon instanceof Knife knife) {
                x = (gp.tileSize * 7);

                x += gp.tileSize;
                y += gp.tileSize;
                text = "What do you wish to do?";
                drawTextBold(text, x, y, 45);

                x += gp.tileSize/2;
                y += gp.tileSize;
                text = "Stab";
                drawTextBold(text, x, y, 45);
                if (commandNum == 0) {
                    g2.drawString(">", x - gp.tileSize / 2, y);
                }

                g2.drawImage(knife.getImage(), x + gp.tileSize*5, y - gp.tileSize/2, gp.tileSize, gp.tileSize, null);

                text = "Damage: " + knife.getDamage() + " pts";
                drawText(text, x, y+gp.tileSize/2, 40);

                y += gp.tileSize + gp.tileSize/4;
                text = "Back";
                drawTextBold(text, x, y+gp.tileSize/4, 45);
                if (commandNum == 1) {
                    g2.drawString(">", x - gp.tileSize / 2, y+gp.tileSize/4);
                }
            }
        } else if (combH.getCombatScreenState() == 2 || combH.getCombatScreenState() == 3 || combH.getCombatScreenState() == 4) {

            drawTextBold(message, x + gp.tileSize/2, y + gp.tileSize, 45);
        }

        combH.setScene(g2);

        if (combH.getCombatScreenState() == 10) {
            x = gp.tileSize*8;
            y = gp.tileSize*3;
            drawItemsBox(x, y, gp.getPlayer());
        }
    }

    private void drawStats() {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/border.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, 0, 0, gp.screenWidth, gp.hudHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));

        String text = "Life";
        int x = gp.tileSize/2;
        int y = gp.tileSize*3/4;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        /* -------------------- PLAYER STATUS ------------------------- */

        text = "Hunger";
        y += gp.tileSize*5/8;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Thirst";
        y += gp.tileSize*5/8;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x += gp.tileSize*2;

        y = gp.tileSize/2 - gp.tileSize/8;
        drawPlayerLife(x, (gp.tileSize/2 - gp.tileSize/8));

        y += (gp.tileSize/2 + gp.tileSize/6);
        drawPlayerHunger(x, y);

        y += (gp.tileSize/2 + gp.tileSize/6);
        drawPlayerThirst(x, y);

        x = gp.tileSize*9 + gp.tileSize/8;
        y = gp.tileSize*3/4;

        text = "Energy";
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        y += gp.tileSize*5/8;

        text = "Sanity";
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        y += gp.tileSize*5/8;

        text = "Cond: ";
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x += gp.tileSize*2;
        y = gp.tileSize/2 - gp.tileSize/8;
        drawPlayerEnergy(x, y);

        y += (gp.tileSize/2 + gp.tileSize/6);
        drawPlayerSanity(x, y);

        y += (gp.tileSize/2 + gp.tileSize/4);
        drawPlayerConditions(x, y);

        /* --------------------- TIME STATUS ------------------------ */

        text = "Days: " + gp.getDays();
        y = gp.tileSize*3/4;
        x = gp.tileSize*7;

        drawTextBold(text, x, y, 40);

        y += gp.tileSize*5/8;

        displayHours(x, y);
    }

    private void displayHours(int x, int y) {
        int time = gp.getTime();

        int hours = time / 1000;
        int minutes = (int) ((time % 1000) * 60 / 1000.0);

        hours = hours % 24;

        String text = String.format("%02d:%02d", hours, minutes);

        drawTextBold(text, x, y, 40);
    }

    private void drawPlayerLife(int screenX, int screenY) {
        g2.setColor(Color.red);
        int playerLife = gp.getPlayer().getLife();

        int numberOfBlocks = (22*playerLife)/100;

        for (int i = 0; i < numberOfBlocks; i++) {
            g2.fillRect(screenX, screenY, 8, 24);
            screenX += 12;
        }
    }

    private void drawEnemyLife(int screenX, int screenY) {
        g2.setColor(Color.red);
        int enemyIndex = gp.getPlayer().getCurrentEnemy();
        int enemyLife = gp.getEnemy(enemyIndex).getLife();

        int numberOfBlocks = (22*enemyLife)/100;

        for (int i = 0; i < numberOfBlocks; i++) {
            g2.fillRect(screenX, screenY, 8, 24);
            screenX += 12;
        }
    }

    private void drawPlayerHunger(int screenX, int screenY) {
        g2.setColor(Color.green);
        int playerLife = gp.getPlayer().getHunger();

        int numberOfBlocks = (22*playerLife)/100;

        for (int i = 0; i < numberOfBlocks; i++) {
            g2.fillRect(screenX, screenY, 8, 24);
            screenX += 12;
        }
    }

    private void drawPlayerThirst(int screenX, int screenY) {
        g2.setColor(Color.blue);
        int playerLife = gp.getPlayer().getThirst();

        int numberOfBlocks = (22*playerLife)/100;

        for (int i = 0; i < numberOfBlocks; i++) {
            g2.fillRect(screenX, screenY, 8, 24);
            screenX += 12;
        }
    }

    private void drawPlayerSanity(int screenX, int screenY) {
        g2.setColor(Color.green);
        int playerLife = gp.getPlayer().getSanity();

        int numberOfBlocks = (22*playerLife)/100;

        for (int i = 0; i < numberOfBlocks; i++) {
            g2.fillRect(screenX, screenY, 8, 24);
            screenX += 12;
        }
    }

    private void drawPlayerConditions(int screenX, int screenY) {

        List<SicknessInjuryEvent> conditions = gp.getPlayer().getConditions();

        for (SicknessInjuryEvent cond : conditions) {
            switch (cond.getName()) {
                case "hypotermia":
                    drawText("Hyp", screenX + 10, screenY + 10, 35);
                    break;
                case "fever":
                    drawText("Fev", screenX + 10, screenY + 10, 35);
                    break;
                case "dehydration":
                    drawText("Deh", screenX + 10, screenY + 10, 35);
                    break;
            }
        }
    }

    private void drawPlayerEnergy(int screenX, int screenY) {
        g2.setColor(Color.yellow);
        int playerLife = gp.getPlayer().getEnergy();

        int numberOfBlocks = (22*playerLife)/100;

        for (int i = 0; i < numberOfBlocks; i++) {
            g2.fillRect(screenX, screenY, 8, 24);
            screenX += 12;
        }
    }

    private void drawTitleScreen() {

        // the 0 screen is the title screen
        if (titleScreenState == 0) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100));

            String text = "The Last Frontier";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // Shadow
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));

            text = "New Game";
            x = getXforCenteredText(text);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Quit";
            x = getXforCenteredText(text);
            y += gp.tileSize + gp.tileSize/2;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));

            String text = "Select your class";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            // shadow
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 65));

            text = "Survivor";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Medic";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Scientist";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Soldier";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 4) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    private void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    private void drawDialogueScreen() {

        // this sets the position of the dialogue screen in the window
        int x = gp.tileSize;
        int y = gp.tileSize * 9;
        int width = gp.screenWidth - (gp.tileSize*2);
        int height = gp.tileSize * 4;

        drawBox("/hud/dialogue_box.png",x, y, width, height);

        // this is to define where the text is going to be displayed on the window
        x += gp.tileSize;
        y += gp.tileSize;

        drawText(currentDialogue, x, y, 40);
    }

    private void drawBox(String path, int x, int y, int width, int height) {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, width, height, null);
    }

    private void drawTextBold(String text, int x, int y, int size) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, size));

        g2.setColor(Color.white);

        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawText(String text, int x, int y, int size) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, size));

        g2.setColor(Color.white);

        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth/2 - length/2);
    }

    public int getIndexOnSlot() {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }

    public boolean isSlotNotEmpty() {
        return slotNotEmpty;
    }

    public void setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
    }

    public int getTitleScreenState() {
        return titleScreenState;
    }

    public int getInventoryScreenState() {
        return inventoryScreenState;
    }

    public void setInventoryScreenState(int inventoryScreenState) {
        this.inventoryScreenState = inventoryScreenState;
    }

    public void setCurrentDialogue(String dialogue) {
        this.currentDialogue = dialogue;
    }

    public int getCommandNum() {
        return this.commandNum;
    }

    public void setCommandNum(int commandNum, int limit) {

        if (commandNum < 0) {
            this.commandNum = limit;
        } else if (commandNum > limit) {
            this.commandNum = 0;
        } else {
            this.commandNum = commandNum;
        }
    }

    public int getSlotCol() {
        return slotCol;
    }

    public void setSlotCol(int slotCol) {
        this.slotCol = slotCol;
    }

    public int getSlotRow() {
        return slotRow;
    }

    public void setSlotRow(int slotRow) {
        this.slotRow = slotRow;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }
}
