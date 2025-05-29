package game.graphics;

import game.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {

    private GamePanel gp;
    public ArrayList<Tile> tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new ArrayList<>();   // this is the amount of tiles, can be changed to add more tile types

        mapTileNum = new int[gp.maxEnvCol][gp.maxEnvRow];

        getTileImage();
    }

    public void getTileImage() {
        // this method call the loadtileset method, passing the file path
        loadTileSet("/environment/tileset1.png");
    }

    private void loadTileSet(String path) {
        // this method loads the tile set
        SpriteSheet tileset = new SpriteSheet(path);

        int height = tileset.getHeight();
        int width = tileset.getWidth();

        int tileSize = 16;

        for (int y = 0; y < height; y += tileSize) {
            for (int x = 0; x < width; x += tileSize) {
                this.tile.add(new Tile());

                this.tile.getLast().image = tileset.getFrame(x, y, tileSize, tileSize);
                BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile.getLast().image.getType());
                Graphics2D g2 = scaledImage.createGraphics();
                g2.drawImage(tile.getLast().image, 0, 0, gp.tileSize, gp.tileSize, null);


            }
        }

        // setar a colisão de respectivos tiles
        this.tile.get(6).collision = true;

        for (int i = 0; i <= 46; i += 30) {
            for (int j = 7; j <= 16; j++) {
                this.tile.get(i + j).collision = true;
            }
        }

        for (int i = 90; i <= 150; i += 30) {
            for (int j = 0; j <= 4; j++) {
                this.tile.get(i + j).collision = true;
            }
        }

        this.tile.get(68).collision = true;
        this.tile.get(69).collision = true;
        this.tile.get(71).collision = true;
        this.tile.get(72).collision = true;
        this.tile.get(75).collision = true;
        this.tile.get(76).collision = true;
        this.tile.get(101).collision = true;
        this.tile.get(102).collision = true;

    }


    public void loadMap(String filePath) {
        // this method loads the environment maps

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxEnvCol && row < gp.maxEnvRow) {
                String line = br.readLine();

                while (col < gp.maxEnvCol) {
                    String[] numbers = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    col++;
                }

                if (col == gp.maxEnvCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        Player player = gp.getPlayer();
        int playerEnvX = player.getEnvX();
        int playerEnvY = player.getEnvY();

        int envCol = 0;
        int envRow = 0;

        while (envCol < gp.maxEnvCol && envRow < gp.maxEnvRow) {

            int tileNum = mapTileNum[envCol][envRow];

            // code that makes the camera 'follow' the character
            int envX = envCol * gp.tileSize;
            int envY = envRow * gp.tileSize;
            int screenX = envX - playerEnvX + player.screenX;
            int screenY = envY - playerEnvY + player.screenY;


            /* ------ stop moving the camera at the edge  -------- */

            if (player.screenX > playerEnvX) {
                screenX = envX;
            }
            if (player.screenY > playerEnvY + gp.hudHeight) {
                screenY = envY + gp.hudHeight;
            }
            int rightOffset = gp.screenWidth - player.screenX;
            if (rightOffset > gp.envWidth - playerEnvX) {
                screenX = gp.screenWidth - (gp.envWidth - envX);
            }
            int bottomOffset = gp.screenHeight - player.screenY;
            if (bottomOffset > gp.envHeight - playerEnvY) {
                screenY = gp.screenHeight - (gp.envHeight - envY);
            }

            if (envX + gp.tileSize > playerEnvX - player.screenX &&
                envX - gp.tileSize < playerEnvX + player.screenX &&
                envY + gp.tileSize > playerEnvY - player.screenY &&
                envY - gp.tileSize < playerEnvY + player.screenY) {

                g2.drawImage(this.tile.get(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            } else if (player.screenX > playerEnvX ||
                        player.screenY > playerEnvY ||
                        rightOffset > gp.envWidth - playerEnvX ||
                        bottomOffset > gp.envHeight - playerEnvY) {

                // this condition deals with the rendering of tiles when the player is in the border area
                g2.drawImage(this.tile.get(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            envCol++;

            if (envCol == gp.maxEnvCol) {
                envCol = 0;
                envRow++;
            }
        }

    }
}
