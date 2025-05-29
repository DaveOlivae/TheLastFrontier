package game;

import game.entity.Entity;
import game.entity.Player;
import game.graphics.GamePanel;

import java.awt.*;
import java.util.Random;

public class CombatHandler {

    private Player player;
    private Entity target;
    private String turnMessage;
    private int playerX, playerY;
    private int targetX, targetY;

    public CombatHandler(Player player, Entity target) {
        this.player = player;
        this.target = target;

        this.playerX = player.getEnvX();
        this.playerY = player.getEnvY();

        this.targetX = target.getEnvX();
        this.targetY = target.getEnvY();

        this.turnMessage = "A " + target.getName() + " appeared!";
    }

    public void turn() {

    }

    public void setScene(Graphics2D g2, GamePanel gp) {
        // player
        player.setForFight(g2, gp.tileSize*6, gp.tileSize*6, "right");

        // target
        target.setForFight(g2, gp.tileSize*9, gp.tileSize*6, "left");
    }

    public void run(GamePanel gp) {
        Random random = new Random();

        int roll = random.nextInt(10);

        if (roll <= 4) {
            turnMessage = "You weren't able to \nescape";
        } else {
            // these coordinates set the player and the enemy to its original position at the beginning of the game
            player.setEnvX(playerX);
            player.setEnvY(playerY);

            target.setEnvX(targetX);
            target.setEnvY(targetY);

            gp.gameState = gp.playState;
        }
    }

    public Entity getTarget() {
        return target;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTurnMessage() {
        return turnMessage;
    }
}
