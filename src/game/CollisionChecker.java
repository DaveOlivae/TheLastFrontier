package game;

import game.ambientes.Ambiente;
import game.ambientes.GerenciadorDeAmbiente;
import game.entity.Entity;
import game.graphics.GamePanel;
import game.itens.Item;

import java.util.List;

public class CollisionChecker {

    private GamePanel gp;
    private GerenciadorDeAmbiente envM;

    public CollisionChecker(GamePanel gp, GerenciadorDeAmbiente envM) {
        this.envM = envM;
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.envX + entity.solidArea.x;
        int entityRightWorldX = entity.envX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.envY + entity.solidArea.y;
        int entityBottomWorldY = entity.envY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile.get(tileNum1).collision || gp.tileM.tile.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        List<Item> itens = gp.getItens();

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            if (itens.get(i) != null) {
                // get entity's solid area position
                    // we take the value of the rectangle x, y and add the environment x, y to get the value on the env
                entity.solidArea.x = entity.envX + entity.solidArea.x;
                entity.solidArea.y = entity.envY + entity.solidArea.y;

                // get the object's solid area position
                item.solidArea.x = item.envX + item.solidArea.x;
                item.solidArea.y = item.envY + item.solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(item.solidArea)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                item.solidArea.x = item.solidAreaDefaultX;
                item.solidArea.y = item.solidAreaDefaultY;
            }

        }

        return index;
    }
}
