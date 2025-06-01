package game.ambientes;

import game.entity.Entity;
import game.graphics.GamePanel;
import game.graphics.TileManager;
import game.itens.*;

import java.util.*;

public abstract class Environment {
    private String name;
    private List<Item> itens;
    private List<Entity> npcs;
    private List<Entity> enemies;
    private TileManager tileM;
    private GamePanel gp;
    private String envPath;

    public Environment(String name, TileManager tileM, GamePanel gp) {
        this.name = name;
        this.gp = gp;
        this.enemies = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.tileM = tileM;
    }

    /* criacao e manutencao do ambiente */

    public void loadEnvironment(String path) {
        tileM.loadMap(path);
    }

    public void loadEnvironment() {
        loadEnvironment(envPath);
    }

    public void addNPC(Entity npc, int envX, int envY) {
        npc.setEnvX(envX);
        npc.setEnvY(envY);
        npcs.add(npc);
    }

    public void addItem(Item item, int envX, int envY) {
        item.envX = envX;
        item.envY = envY;
        itens.add(item);
    }

    public void addEnemy(Entity enemy, int envX, int envY) {
        enemy.setEnvX(envX);
        enemy.setEnvY(envY);
        enemies.add(enemy);
    }

    public void removeEnemy(Entity enemy) {
        enemies.remove(enemy);
    }

    // gets e sets

    public Entity getEnemy(int index) {
        return this.enemies.get(index);
    }

    public List<Entity> getEnemies() {
        return this.enemies;
    }

    public List<Item> getItens() {
        return this.itens;
    }

    public Entity getNPC(int index) {
        return this.npcs.get(index);
    }

    public List<Entity> getNPCs() {
        return this.npcs;
    }

    public GamePanel getGp() {
        return this.gp;
    }

    public String getName() {
        return this.name;
    }

    public void setEnvPath(String envPath) {
        this.envPath = envPath;
    }
}
