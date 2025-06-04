package game.logic;

import game.entity.Entity;
import game.entity.Player;
import game.entity.enemies.Enemy;
import game.graphics.GamePanel;
import game.itens.weapons.Firearm;
import game.itens.weapons.Weapon;

import java.awt.*;
import java.util.Random;

public class CombatHandler {

    private GamePanel gp;
    private Player player;
    private Enemy target;
    private String turnMessage;
    private int playerX, playerY;
    private int targetX, targetY;
    private int combatScreenState;  // i preferred to put this here because the state of combat is key to the overral combat flow, not just the ui

    public CombatHandler(Player player, Entity target, GamePanel gp) {
        this.gp = gp;
        this.player = player;
        this.target = (Enemy) target;

        this.playerX = player.getEnvX();
        this.playerY = player.getEnvY();

        this.targetX = target.getEnvX();
        this.targetY = target.getEnvY();

        this.turnMessage = "A " + target.getName() + " appeared!";
    }

    public void playerAttack() {
        Weapon weapon = (Weapon) player.getEquippedItem();
        int range = weapon.getRange();
        int damagePoints = weapon.getDamage();

        double chanceOfShot = Math.min(1.0, range/10.0);

        double diceRoll = Math.random();

        if (weapon instanceof Firearm gun && gun.getLoad() == 0) {
            player.reloadGun(gun);

            // this if deals with the situation where theres no more ammo on the gun and the player also doesnt have ammo
            if (player.getPlayerAmmo(gun.getFirearmType()).getQuantity() == 0 && gun.getLoad() == 0) {
                turnMessage = "You don't have\nammo left.";
            }
        } else {
            if (diceRoll < chanceOfShot) {
                target.damage(damagePoints);

                if (weapon instanceof Firearm gun) {
                    turnMessage = "You shot the " + target.getName() + "\n" +
                            "You gave " + damagePoints + " points of\ndamage";

                    gun.shotGun();
                } else {
                    turnMessage = "You stabbed the " + target.getName() + "\n" +
                            "You gave " + damagePoints + " points of\ndamage";
                }

                player.subtractUseItem();
            } else {
                if (weapon instanceof Firearm gun) {
                    gun.shotGun();
                }
                turnMessage = "You missed!";
                player.subtractUseItem();
            }
        }

        if (target.getLife() == 0) {
            turnMessage = "The enemy died";
            combatScreenState = 4;
        } else {
            combatScreenState = 2;
        }
    }

    public void targetAttack() {
        Weapon weapon = (Weapon) target.getEquippedItem();
        int range = weapon.getRange();
        int damagePoints = weapon.getDamage();

        double chanceOfShot = Math.min(1.0, range/10.0);

        double diceRoll = Math.random();

        if (diceRoll < chanceOfShot) {
            player.damage(damagePoints);
            turnMessage = "The " + target.getName() + " shot you!\n" +
                    "You took " + damagePoints + " points of\ndamage";
        } else {
            turnMessage = "The " + target.getName() + " missed!";
        }

        if (player.getLife() == 0) {
            turnMessage = "You died!";
            combatScreenState = 4;
        } else {
            combatScreenState = 3;
        }
    }

    public void setScene(Graphics2D g2) {
        // player
        player.setForFight(g2, gp.tileSize*6, gp.tileSize*6, "right");

        // target
        target.setForFight(g2, gp.tileSize*9, gp.tileSize*6, "left");
    }

    public void run() {
        Random random = new Random();

        int roll = random.nextInt(10);

        if (roll <= 4) {
            turnMessage = "You weren't able to \nescape";
            combatScreenState = 2;
        } else {
            // these coordinates set the player and the enemy to its original position at the beginning of the game
            setCharsAtOrgPos();

            gp.gameState = gp.playState;
        }
    }

    public void setCharsAtOrgPos() {
        player.setEnvX(playerX);
        player.setEnvY(playerY);

        target.setEnvX(targetX);
        target.setEnvY(targetY);
    }

    public void removeEnemy() {
        gp.getEnvM().getCurrentEnv().removeEnemy(target);
    }

    public int getCombatScreenState() {
        return combatScreenState;
    }

    public void setCombatScreenState(int combatScreenState) {
        this.combatScreenState = combatScreenState;
    }

    public void setTurnMessage(String turnMessage) {
        this.turnMessage = turnMessage;
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
