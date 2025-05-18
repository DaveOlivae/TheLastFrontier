package game.itens;

import game.graphics.GamePanel;

public class ItemManager {

    GamePanel gp;

    public ItemManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Cantil();
        gp.obj[0].worldX = 16 * gp.tileSize;
        gp.obj[0].worldY = 13 * gp.tileSize;

    }
}
