package game.graphics;

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
