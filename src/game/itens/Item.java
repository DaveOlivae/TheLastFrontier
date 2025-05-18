package game.itens;

import game.entity.Player;
import game.graphics.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

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
        int screenX = worldX - gp.player.envX + gp.player.screenX;
        int screenY = worldY - gp.player.envY + gp.player.screenY;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void usar(Player jogador) {
        // i have to see what i'm gonna do about this
    }

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
