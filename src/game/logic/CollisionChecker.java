package game.logic;

import game.ambientes.EnvironmentManager;
import game.entity.Entity;
import game.graphics.GamePanel;
import game.itens.Item;

import java.awt.*;
import java.util.List;

public class CollisionChecker {

    private GamePanel gp;
    private EnvironmentManager envM;

    public CollisionChecker(GamePanel gp, EnvironmentManager envM) {
        this.envM = envM;
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        Rectangle entitySolidArea = entity.getSolidArea();
        String entityDirection = entity.getDirection();
        int entitySpeed = entity.getSpeed();
        int entityEnvX = entity.getEnvX();
        int entityEnvY = entity.getEnvY();

        int entityLeftWorldX = entityEnvX + entitySolidArea.x;
        int entityRightWorldX = entityEnvX + entitySolidArea.x + entitySolidArea.width;
        int entityTopWorldY = entityEnvY + entitySolidArea.y;
        int entityBottomWorldY = entityEnvY + entitySolidArea.y + entitySolidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entityDirection) {
            case "up":
                entityTopRow = (entityTopWorldY - entitySpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entitySpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entitySpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entitySpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        // this checks either npc or player collision with objects

        int index = 999;

        Rectangle entitySolidArea = entity.getSolidArea();
        String entityDirection = entity.getDirection();
        int entitySpeed = entity.getSpeed();
        int entityEnvX = entity.getEnvX();
        int entityEnvY = entity.getEnvY();

        List<Item> itens = gp.getItens();

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            if (itens.get(i) != null) {
                // get entity's solid area position
                    // we take the value of the rectangle x, y and add the environment x, y to get the value on the env
                entitySolidArea.x = entityEnvX + entitySolidArea.x;
                entitySolidArea.y = entityEnvY + entitySolidArea.y;

                // get the object's solid area position
                item.solidArea.x = item.envX + item.solidArea.x;
                item.solidArea.y = item.envY + item.solidArea.y;

                switch (entityDirection) {
                    case "up":
                        entitySolidArea.y -= entitySpeed;
                        if (entitySolidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entitySolidArea.y += entitySpeed;
                        if (entitySolidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entitySolidArea.x -= entitySpeed;
                        if (entitySolidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entitySolidArea.x += entitySpeed;
                        if (entitySolidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }

                entitySolidArea.x = entity.getSolidAreaDefaultX();
                entitySolidArea.y = entity.getSolidAreaDefaultY();
                item.solidArea.x = item.solidAreaDefaultX;
                item.solidArea.y = item.solidAreaDefaultY;
            }

        }

        return index;
    }

    public int checkEntity(Entity entity, List<Entity> target) {
        // this method is used to check if there are any npcs and if the player is going to collide with them
        // it check the two blocks right in front of the player, considering the direction the player is headed

        int index = 999;

        Rectangle entitySolidArea = entity.getSolidArea();
        String entityDirection = entity.getDirection();
        int entitySpeed = entity.getSpeed();
        int entityEnvX = entity.getEnvX();
        int entityEnvY = entity.getEnvY();

        for (int i = 0; i < target.size(); i++) {
            Entity npc = target.get(i);

            if (target.get(i) != null) {
                // get entity's solid area position
                // we take the value of the rectangle x, y and add the environment x, y to get the value on the env
                entitySolidArea.x = entityEnvX + entitySolidArea.x;
                entitySolidArea.y = entityEnvY + entitySolidArea.y;

                // get the object's solid area position
                npc.getSolidArea().x = npc.getEnvX() + npc.getSolidArea().x;
                npc.getSolidArea().y = npc.getEnvY() + npc.getSolidArea().y;

                switch (entityDirection) {
                    case "up":
                        entitySolidArea.y -= entitySpeed;
                        if (entitySolidArea.intersects(npc.getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entitySolidArea.y += entitySpeed;
                        if (entitySolidArea.intersects(npc.getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entitySolidArea.x -= entitySpeed;
                        if (entitySolidArea.intersects(npc.getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entitySolidArea.x += entitySpeed;
                        if (entitySolidArea.intersects(npc.getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }

                entitySolidArea.x = entity.getSolidAreaDefaultX();
                entitySolidArea.y = entity.getSolidAreaDefaultY();

                npc.getSolidArea().x = npc.getSolidAreaDefaultX();
                npc.getSolidArea().y = npc.getSolidAreaDefaultY();
            }

        }
        return index;
    }

    public void checkPlayer(Entity entity) {
        // this method is used to check if the player is going to collide with the player

        Entity player = gp.getPlayer();

        Rectangle entitySolidArea = entity.getSolidArea();
        String entityDirection = entity.getDirection();
        int entitySpeed = entity.getSpeed();
        int entityEnvX = entity.getEnvX();
        int entityEnvY = entity.getEnvY();

        // get entity's solid area position
        // we take the value of the rectangle x, y and add the environment x, y to get the value on the env
        entitySolidArea.x = entityEnvX + entitySolidArea.x;
        entitySolidArea.y = entityEnvY + entitySolidArea.y;

        // get the object's solid area position
        player.getSolidArea().x = player.getEnvX() + player.getSolidArea().x;
        player.getSolidArea().y = player.getEnvY() + player.getSolidArea().y;

        switch (entityDirection) {
            case "up":
                entitySolidArea.y -= entitySpeed;
                if (entitySolidArea.intersects(player.getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entitySolidArea.y += entitySpeed;
                if (entitySolidArea.intersects(player.getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entitySolidArea.x -= entitySpeed;
                if (entitySolidArea.intersects(player.getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entitySolidArea.x += entitySpeed;
                if (entitySolidArea.intersects(player.getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
        }

        entitySolidArea.x = entity.getSolidAreaDefaultX();
        entitySolidArea.y = entity.getSolidAreaDefaultY();

        player.getSolidArea().x = player.getSolidAreaDefaultX();
        player.getSolidArea().y = player.getSolidAreaDefaultY();
    }

    public void checkBorders(Entity entity) {
        // this function is used to check if the npcs are going to go out of the environment, they are not supposed to
        // so the borders are like walls for them
        // actually they can only walk up until 1 tile before the border
        Rectangle entitySolidArea = entity.getSolidArea();
        String entityDirection = entity.getDirection();
        int entitySpeed = entity.getSpeed();
        int entityEnvX = entity.getEnvX();
        int entityEnvY = entity.getEnvY();

        // get entity's solid area position
        // we take the value of the rectangle x, y and add the environment x, y to get the value on the env
        entitySolidArea.x = entityEnvX + entitySolidArea.x;
        entitySolidArea.y = entityEnvY + entitySolidArea.y;

        // get the object's solid area position

        switch (entityDirection) {
            case "up":
                entitySolidArea.y -= entitySpeed;
                if (entitySolidArea.y < gp.tileSize) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entitySolidArea.y += entitySpeed;
                if (entitySolidArea.y > gp.envHeight - gp.tileSize) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entitySolidArea.x -= entitySpeed;
                if (entitySolidArea.x < gp.tileSize) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entitySolidArea.x += entitySpeed;
                if (entitySolidArea.x > gp.envHeight - gp.tileSize) {
                    entity.setCollisionOn(true);
                }
                break;
        }

        entitySolidArea.x = entity.getSolidAreaDefaultX();
        entitySolidArea.y = entity.getSolidAreaDefaultY();

    }
}
