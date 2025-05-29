package game.graphics;

import game.CombatHandler;
import game.ambientes.Environment;
import game.ambientes.EnvironmentManager;
import game.entity.Player;
import game.itens.Item;

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
    }

    private void drawInventory() {
        int x = gp.tileSize;
        int y = gp.tileSize * 3;
        int width = gp.tileSize*5;
        int height = gp.tileSize*10;

        Player player = gp.getPlayer();
        List<Item> itens = player.itens();

        BufferedImage image;

        /* ------------- attributes box --------------- */
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/stats_box.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, x, y, width, height, null);

        x += gp.tileSize/2;
        y += gp.tileSize;
        String text = "HP: " + player.getLife() + "/" + player.getMaxLife();
        drawTextBold(text, x, y, 40);

        y += 40;
        text = "Hunger: " + player.getHunger() + "/" + player.getMaxHunger();
        drawTextBold(text, x, y, 40);

        y += 40;
        text = "Thirst: " + player.getThirst() + "/" + player.getMaxThirst();
        drawTextBold(text, x, y, 40);

        y += 40;
        text = "Energy: " + player.getEnergy() + "/" + player.getMaxEnergy();
        drawTextBold(text, x, y, 40);

        y += 40;
        text = "Sanity: " + player.getSanity() + "/" + player.getMaxSanity();
        drawTextBold(text, x, y, 40);

        /* ------------- inventory box --------------- */

        x = gp.tileSize*8;
        y = gp.tileSize*3;

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

            slotX += (gp.tileSize + 24);

            if (i == 4 || i == 9 || i == 14 || i == 19) {
                slotX = startX;
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

        int itemIndex = getIndexOnSlot();
        if (itemIndex < itens.size()) {
            drawText(itens.get(itemIndex).getDescription(), x + gp.tileSize/2, y + 55, 45);
        }

        /* --------------- selecting an item ----------------------- */

        if (inventoryScreenState == 1 && itemIndex < itens.size()) {
            String itemType = itens.get(itemIndex).getType();
            int boxX = selX + gp.tileSize/2;
            int boxY = selY + gp.tileSize/2;

            drawBox("/hud/options_box.png", boxX, boxY, gp.tileSize*3, gp.tileSize*3);

            int stringX = boxX + gp.tileSize/2 + gp.tileSize/6;
            int stringY = boxY + 60;

            text = "Equip";
            drawText(text, stringX, stringY, 45);
            if (commandNum == 0) {
                g2.drawString(">", stringX - gp.tileSize/4, stringY);
            }

            stringY += 50;
            text = "Remove";
            drawText(text, stringX, stringY, 45);
            if (commandNum == 1) {
                g2.drawString(">", stringX - gp.tileSize/4, stringY);
            }

            stringY += 50;
            text = "Cancel";
            drawText(text, stringX, stringY, 45);
            if (commandNum == 2) {
                g2.drawString(">", stringX - gp.tileSize/4, stringY);
            }
        }
    }

    private void drawCombatScreen() {
        // the battle map will differ, depending on the environment the player is at

        EnvironmentManager envM = gp.getEnvM();
        Environment currentEnv = envM.getCurrentEnv();
        CombatHandler combH = gp.getCombatHandler();

        BufferedImage image;

        String message = combH.getTurnMessage();

        String path = "/hud/battle_mat_" + currentEnv.getName() + ".png";

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
        drawTextWithShadow(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        x = gp.tileSize*2 + gp.tileSize/2;
        y = gp.tileSize*2 + gp.tileSize/4;

        // life bar
        drawPlayerLife(x, y);

        // life number
        int maxLife = combH.getPlayer().getMaxLife();
        int life = combH.getPlayer().getLife();

        text = maxLife + "/" + life;

        drawTextWithShadow(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        /* ---------- enemy stats ------------- */
        x = gp.tileSize*9;
        y = gp.tileSize;
        text = combH.getTarget().getName();

        // enemy name
        drawTextWithShadow(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        x += gp.tileSize/2;
        y = gp.tileSize*2 + gp.tileSize/4;

        // enemy life
        drawEnemyLife(x, y);

        // life number
        maxLife = combH.getTarget().getMaxLife();
        life = combH.getTarget().getLife();

        text = maxLife + "/" + life;

        drawTextWithShadow(text, x + gp.tileSize/2, y + gp.tileSize, 45);

        /* ----------- status box -------------- */
        x = gp.tileSize/2;
        y = (gp.tileSize*9) + gp.tileSize/8;

        drawTextBold(message, x + gp.tileSize/2, y + gp.tileSize, 45);

        /* ------------ options box -------------- */
        x = (gp.tileSize*7);

        x += gp.tileSize;
        y += gp.tileSize;
        text = "What do you wish to do?";
        drawTextBold(text, x, y, 45);

        x += gp.tileSize;
        y += gp.tileSize;
        text = "Attack";
        drawTextBold(text, x, y, 45);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize/2, y);
        }

        text = "Run";
        drawTextBold(text, x + 4*gp.tileSize, y, 45);
        if (commandNum == 2) {
            g2.drawString(">", x + 3*gp.tileSize + gp.tileSize/2, y);
        }

        y += gp.tileSize;
        text = "Item";
        drawTextBold(text, x, y, 45);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize/2, y);
        }

        combH.setScene(g2, gp);

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

        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Hunger";
        y += gp.tileSize*5/8;

        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Energy";
        y += gp.tileSize*5/8;

        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        drawPlayerLife(gp.tileSize*5/2, (gp.tileSize/2 - gp.tileSize/8));
        drawPlayerHunger();
        drawPlayerEnergy();
    }

    private void drawPlayerLife(int screenX, int screenY) {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/health_bar_full.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, screenX, screenY, 4*gp.tileSize, gp.tileSize/2, null);
    }

    private void drawEnemyLife(int screenX, int screenY) {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/health_bar_full.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, screenX, screenY, 4*gp.tileSize, gp.tileSize/2, null);
    }

    private void drawPlayerHunger() {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/hunger_bar_full.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, gp.tileSize*5/2, gp.tileSize + gp.tileSize/32, 4*gp.tileSize, gp.tileSize/2, null);
    }

    private void drawPlayerEnergy() {
        BufferedImage image;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hud/energy_bar_full.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(image, gp.tileSize*5/2, gp.tileSize*3/2 + gp.tileSize/8 + gp.tileSize/16, 4*gp.tileSize, gp.tileSize/2, null);
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
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Load Game";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Quit";
            x = getXforCenteredText(text);
            y += gp.tileSize;
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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(8));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
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

    private void drawTextWithShadow(String text, int x, int y, int size) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, size));

        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);
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
