package game.graphics;

import game.CollisionChecker;
import game.ambientes.EnvironmentManager;
import game.entity.Player;
import game.entity.Rastreador;
import game.input.KeyHandler;
import game.itens.Item;
import game.itens.ItemManager;

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

    int FPS = 60;

    // instantiating

    EnvironmentManager envM = new EnvironmentManager();

    public TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);

    public ItemManager itemM = new ItemManager(this);

    public Player player = new Rastreador("none", this, keyH);

    public Item[] obj = new Item[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        // this method is used to set the objects in the map
        itemM.setObject();
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
        player.update();
    }

    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }

        }

        player.draw(g2);

        envM.update(player, this, tileM);

        g2.dispose();
    }
}
