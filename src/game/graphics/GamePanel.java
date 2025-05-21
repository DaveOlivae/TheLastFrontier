package game.graphics;

import game.CollisionChecker;
import game.Sound;
import game.ambientes.Ambiente;
import game.ambientes.GerenciadorDeAmbiente;
import game.entity.Entity;
import game.entity.Player;
import game.entity.Rastreador;
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
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // environment settings
    public final int maxEnvCol = 30;
    public final int maxEnvRow = 30;
    public final int envWidth = tileSize * maxEnvCol;
    public final int envHeight = tileSize * maxEnvRow;

    // game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    int FPS = 60;

    // system
    private GerenciadorDeAmbiente envM = new GerenciadorDeAmbiente(this);
    public TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler(this);
    private Sound music = new Sound();
    private Thread gameThread;
    private CollisionChecker cChecker = new CollisionChecker(this, envM);
    private UI ui = new UI(this);

    // entities and items
    private Player player = new Rastreador("none", this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        // this method is used to set sound and game state
        playMusic(0);

        gameState = playState;
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
        Ambiente ambienteAtual = envM.getAmbienteAtual();

        if (gameState == playState) {
            player.update();

            for (Entity npc : ambienteAtual.getNpc()) {
                if (npc != null) {
                    npc.update();
                }
            }
        }
    }

    private void drawObjects(Graphics2D g2) {
        Ambiente ambienteAtual = envM.getAmbienteAtual();

        for (Item item : ambienteAtual.getItens()) {
            if (item != null) {
                item.draw(g2, this);
            }
        }
    }

    private void drawNPC(Graphics2D g2) {
        Ambiente ambienteAtual = envM.getAmbienteAtual();

        for (Entity npc : ambienteAtual.getNpc()) {
            if (npc != null) {
                npc.draw(g2);
            }
        }
    }

    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        envM.update(player, tileM);

        // TILE
        tileM.draw(g2);

        drawObjects(g2);

        drawNPC(g2);

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();
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

    public Player getPlayer() {
        return this.player;
    }

    public CollisionChecker getcChecker() {
        return this.cChecker;
    }

    public List<Entity> getNPCs() {
        return envM.getAmbienteAtual().getNpc();
    }

    public List<Item> getItens() {
        return envM.getAmbienteAtual().getItens();
    }
}
