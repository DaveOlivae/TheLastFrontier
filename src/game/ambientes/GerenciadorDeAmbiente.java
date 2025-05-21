package game.ambientes;

import game.entity.Player;
import game.graphics.GamePanel;
import game.graphics.TileManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GerenciadorDeAmbiente {
    private String climaGlobal;

    private final int worldMapWidth = 21;
    private final int worldMapHeight = 21;

    private int[][] worldMapNum;
    private Ambiente[][] historico;
    private Ambiente ambienteAtual;

    private int playerMapX;  // these are the coordinates for the player, in the world map
    private int playerMapY;

    public GerenciadorDeAmbiente() {

        this.playerMapX = 10;
        this.playerMapY = 18;
        this.worldMapNum = new int[worldMapWidth][worldMapHeight];
        this.historico = new Ambiente[worldMapWidth][worldMapHeight];

        loadMap();
    }

    public void update(Player player, GamePanel gp, TileManager tileM) {
        updateEnvironment(player, gp);
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
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    worldMapNum[col][row] = num;

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

    public void updateEnvironment(Player player, GamePanel gp) {

        if (player.envX > gp.envWidth - gp.tileSize) {
            this.playerMapX++;
            player.envX = 0;
        } else if (player.envX < 0) {
           this.playerMapX--;
           player.envX = gp.envWidth - gp.tileSize;
        } else if (player.envY > gp.envHeight - (gp.tileSize + 5)) {
            this.playerMapY++;
            player.envY = 0;
        } else if (player.envY < 0) {
            this.playerMapY--;
            player.envY = gp.envHeight - 2*gp.tileSize;
        }
    }

    public void changeEnvironment(TileManager tileM) {
        switch (this.worldMapNum[playerMapX][playerMapY]) {
            case 0:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/floresta.csv");
                break;
            case 1:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/floresta1.csv");
                break;
            case 2:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/floresta2.csv");
                break;
            case 3:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/floresta3.csv");
                break;
            case 4:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/floresta4.csv");
                break;
            case 5:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/highway1.csv");
                break;
            case 6:
                if (historico[playerMapX][playerMapY] == null) {
                    ambienteAtual = new AmbienteFloresta(tileM);
                    historico[playerMapX][playerMapY] = ambienteAtual;
                } else {
                    ambienteAtual = historico[playerMapX][playerMapY];
                }
                ambienteAtual.carregarAmbiente("/maps/highway2.csv");
                break;
        }

    }

    public void setClimaGlobal(Ambiente ambiente) {
        this.climaGlobal = ambiente.getClimaAtual();
    }

    public Ambiente getAmbienteAtual() {
        return this.ambienteAtual;
    }

    public String getClimaGlobal() {
        return this.climaGlobal;
    }
}
