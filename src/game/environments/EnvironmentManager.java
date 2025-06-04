package game.environments;

import game.entity.Player;
import game.graphics.GamePanel;
import game.graphics.TileManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EnvironmentManager {

    private final int worldMapWidth = 21;
    private final int worldMapHeight = 21;

    private String[][] worldMapNum;
    private Environment[][] historico;
    private Environment currentEnv;

    private int playerMapX;  // these are the coordinates for the player, in the world map
    private int playerMapY;

    private GamePanel gp;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;

        setInitialState();
    }

    public void setInitialState() {
        this.playerMapX = 10;
        this.playerMapY = 18;
        this.worldMapNum = new String[worldMapWidth][worldMapHeight];
        this.historico = new Environment[worldMapWidth][worldMapHeight];

        loadMap();
    }

    public void update(Player player, TileManager tileM) {
        updateEnvironment(player);
        changeEnvironment(tileM);
    }

    public void loadMap() {

        try {
            InputStream is = getClass().getResourceAsStream("/maps/worldmap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < worldMapWidth && row < worldMapHeight) {
                String line = br.readLine();

                while (col < worldMapWidth) {
                    String[] numbers = line.split(",");

                    worldMapNum[col][row] = numbers[col];

                    col++;
                }

                if (col == worldMapWidth) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEnvironment(Player player) {

        int playerEnvX = player.getEnvX();
        int playerEnvY = player.getEnvY();

        if (playerEnvX > gp.envWidth - gp.tileSize) {
            this.playerMapX++;
            player.setEnvX(0);
        } else if (playerEnvX < 0) {
           this.playerMapX--;
           player.setEnvX(gp.envWidth - gp.tileSize);
        } else if (playerEnvY > gp.envHeight - (gp.tileSize + 5)) {
            this.playerMapY++;
            player.setEnvY(0);
        } else if (playerEnvY < 0) {
            this.playerMapY--;
            player.setEnvY(gp.envHeight - 2*gp.tileSize);
        }
    }

    public void changeEnvironment(TileManager tileM) {

        String envOriginal = worldMapNum[playerMapX][playerMapY];

        String environment = envOriginal.replaceAll("[0-9]", "");
        String numStr = envOriginal.replaceAll("[^0-9]", "");

        int num = Integer.parseInt(numStr);

        switch (environment) {
            case "forest":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new Forest(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
            case "highway":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new Highway(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
            case "mountain":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new Mountain(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
            case "lakeriver":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new LakeRiver(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
            case "cavern":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new Cavern(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
            case "ruins":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new Ruins(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
            case "city":
                if (historico[playerMapX][playerMapY] == null) {
                    currentEnv = new City(tileM, gp, num);
                    historico[playerMapX][playerMapY] = currentEnv;
                } else {
                    currentEnv = historico[playerMapX][playerMapY];
                }
                break;
        }

        currentEnv.loadEnvironment();
    }

    public Environment getCurrentEnv() {
        return this.currentEnv;
    }
}
