package game.graphics;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {

    GamePanel gp;
    public ArrayList<Tile> tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new ArrayList<>();   // this is the amount of tiles, can be changed to add more tile types

        mapTileNum = new int[gp.maxEnvCol][gp.maxEnvRow];

        getTileImage();
//        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        loadTileSet("/environment/tileset.png");

//        SpriteSheet floor = new SpriteSheet("/environment/floor.png");
//        SpriteSheet water = new SpriteSheet("/environment/water.png");

        // grass
//        this.tile[0] = new Tile();
//        this.tile[0].image = floor.getFrame(16, 160, 16, 16);

        // water
//        this.tile[1] = new Tile();
//        this.tile[1].image = water.getFrame(0, 0, 16, 16);
    }

    private void loadTileSet(String path) {
        SpriteSheet tileset = new SpriteSheet(path);

        int height = tileset.getHeight();
        int width = tileset.getWidth();

        int tileSize = 16;

        for (int y = 0; y < height; y += tileSize) {
            for (int x = 0; x < width; x += tileSize) {
                this.tile.add(new Tile());
                this.tile.getLast().image = tileset.getFrame(x, y, tileSize, tileSize);
            }
        }

        // setar a colisão de respectivos tiles

        this.tile.get(2).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(3).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(4).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(16).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(17).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(18).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(36).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(38).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(55).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(59).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(62).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(64).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(65).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(64).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(75).collision = true;  // colisao do tile que eh uma parede
        this.tile.get(76).collision = true;  // colisao do tile que eh uma parede
    }


    public void loadMap(String filePath) {

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

        int envCol = 0;
        int envRow = 0;

        while (envCol < gp.maxEnvCol && envRow < gp.maxEnvRow) {

            int tileNum = mapTileNum[envCol][envRow];

            // code that makes the camera 'follow' the character
            int envX = envCol * gp.tileSize;
            int envY = envRow * gp.tileSize;
            int screenX = envX - gp.player.envX + gp.player.screenX;
            int screenY = envY - gp.player.envY + gp.player.screenY;

            /* ------ stop moving the camera at the edge  -------- */

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

                g2.drawImage(this.tile.get(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            } else if (gp.player.screenX > gp.player.envX ||
                        gp.player.screenY > gp.player.envY ||
                        rightOffset > gp.envWidth - gp.player.envX ||
                        bottomOffset > gp.envHeight - gp.player.envY) {

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
