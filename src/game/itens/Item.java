package game.itens;

import game.entity.Player;
import game.graphics.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {

    private BufferedImage image;
    public String name;
    public boolean collision = false;
    public int envX, envY;
    // these values for the rectangle are set because so the rectangle will be exaclty 1 tile
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDefaultX, solidAreaDefaultY;

    private int peso;
    private int durabilidade;
    private boolean equipavel;

    public Item(String name, int peso, int durabilidade, boolean equipavel) {
        this.name = name;
        this.peso = peso;
        this.durabilidade = durabilidade;
        this.equipavel = equipavel;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        // this method is similar to the draw method of the tile manager
        // it has to deal with the absolute and relative positions of the object, and take care of them when the player
        // is at the borders of the environment

        int screenX = envX - gp.player.envX + gp.player.screenX;
        int screenY = envY - gp.player.envY + gp.player.screenY;

        if (gp.player.screenX > gp.player.envX) {
            screenX = envX;
        }
        if (gp.player.screenY > gp.player.envY) {
            screenY = envY;
        }
        int rightOffset = gp.screenWidth - gp.player.screenX;
        if (rightOffset > gp.envWidth - gp.player.envX) {
            screenX = gp.screenWidth - (gp.envWidth - envX);
        }
        int bottomOffset = gp.screenHeight - gp.player.screenY;
        if (bottomOffset > gp.envHeight - gp.player.envY) {
            screenY = gp.screenHeight - (gp.envHeight - envY);
        }

        if (envX + gp.tileSize > gp.player.envX - gp.player.screenX &&
                envX - gp.tileSize < gp.player.envX + gp.player.screenX &&
                envY + gp.tileSize > gp.player.envY - gp.player.screenY &&
                envY - gp.tileSize < gp.player.envY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        } else if (gp.player.screenX > gp.player.envX ||
                gp.player.screenY > gp.player.envY ||
                rightOffset > gp.envWidth - gp.player.envX ||
                bottomOffset > gp.envHeight - gp.player.envY) {

            // this condition deals with the rendering of tiles when the player is in the border area
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public abstract void usar(Player jogador);

    public void diminuirDurabilidade(int pontos) {
        this.durabilidade -= pontos;
    }

    public void getAttributes() {
        System.out.printf("\tNome: %s%n", getName());
        System.out.printf("\tPeso: %d%n", getPeso());
        System.out.printf("\tDurabilidade: %d%n", getDurabilidade());

        if (this.equipavel) {
            System.out.println("\tEquipável?: Sim");
        } else {
            System.out.println("\tEquipável?: Não");
        }
    }

    public boolean isEquipavel() {
        return this.equipavel;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getDurabilidade() {
        return this.durabilidade;
    }

    public int getEficiencia() {
        return 0;
    }
}
