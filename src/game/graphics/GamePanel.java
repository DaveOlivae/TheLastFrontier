package game.graphics;

import game.events.EventManager;
import game.logic.Clock;
import game.logic.CollisionChecker;
import game.logic.CombatHandler;
import game.Sound;
import game.environments.Environment;
import game.environments.EnvironmentManager;
import game.entity.Entity;
import game.entity.Player;
import game.input.KeyHandler;
import game.itens.Item;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // screen settings
    final int originalTileSize = 16;  // each tile will be 16x16
    final int scale = 4;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int hudHeight = 3*tileSize - tileSize/2;

    // environment settings
    public final int maxEnvCol = 30;
    public final int maxEnvRow = 30;
    public final int envWidth = tileSize * maxEnvCol;
    public final int envHeight = tileSize * maxEnvRow;

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int combatState = 4;
    public final int inventoryState = 5;
    public final int gameOverState = 6;

    int FPS = 60;

    // system
    private EnvironmentManager envM = new EnvironmentManager(this);
    public TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler(this);
    private EventManager eveM = new EventManager();
    private Sound music = new Sound();
    private Thread gameThread;
    private CollisionChecker cChecker = new CollisionChecker(this, envM);
    private Clock clock = new Clock();
    private UI ui = new UI(this);

    // entities and items
    private Player player;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        gameState = titleState;
    }

    public void startGame() {
        gameState = playState;
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // main game loop, using the "delta accumulator" method

        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            envM.update(player, tileM);
            eveM.update(this);
            player.update();
            clock.update();

            Environment currentEnv = envM.getCurrentEnv();
            for (Entity npc : currentEnv.getNPCs()) {
                if (npc != null) {
                    npc.update();
                }
            }

            for (Entity enemy : currentEnv.getEnemies()) {
                if (enemy != null) {
                    enemy.update();
                }
            }
        }
    }

    private void drawObjects(Graphics2D g2) {
        Environment currentEnv = envM.getCurrentEnv();

        if (!(currentEnv == null)) {
            for (Item item : currentEnv.getItens()) {
                if (item != null) {
                    item.draw(g2, this);
                }
            }
        }
    }

    private void drawNPC(Graphics2D g2) {
        Environment currentEnv = envM.getCurrentEnv();

        if (!(currentEnv == null)) {
            for (Entity npc : currentEnv.getNPCs()) {
                if (npc != null) {
                    npc.draw(g2);
                }
            }
        }
    }

    private void drawEnemies(Graphics2D g2) {
        Environment currentEnv = envM.getCurrentEnv();

        if (!(currentEnv == null)) {
            for (Entity enemy : currentEnv.getEnemies()) {
                if (enemy != null) {
                    enemy.draw(g2);
                }
            }
        }
    }


    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {

            // TILE
            tileM.draw(g2);

            drawObjects(g2);

            drawNPC(g2);

            drawEnemies(g2);

            // PLAYER
            player.draw(g2);

            // day time cycle
            clock.draw(g2, this);

            // UI
            ui.draw(g2);

            g2.dispose();
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        music.setFile(i);
        music.play();
    }

    public CombatHandler getCombatHandler() {
        return player.getCombH();
    }

    public Clock getClock() {
        return clock;
    }

    public int getTime() {
        return clock.getTime();
    }

    public int getDays() {
        return clock.getDaysPassed();
    }

    public UI getUi() {
        return this.ui;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public CollisionChecker getcChecker() {
        return this.cChecker;
    }

    public Entity getNPC(int index) {
        return envM.getCurrentEnv().getNPC(index);
    }

    public List<Entity> getNPCs() {
        return envM.getCurrentEnv().getNPCs();
    }

    public List<Entity> getEnemies() {
        return envM.getCurrentEnv().getEnemies();
    }

    public Entity getEnemy(int index) {
        return envM.getCurrentEnv().getEnemy(index);
    }

    public List<Item> getItens() {
        return envM.getCurrentEnv().getItens();
    }

    public EnvironmentManager getEnvM() {
        return envM;
    }
}
