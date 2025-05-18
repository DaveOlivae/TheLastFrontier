package game.ambientes;

import game.entity.Player;
import game.graphics.GamePanel;
import game.graphics.TileManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EnvironmentManager {
    private List<Supplier<Ambiente>> ambientesDisponiveis;
    private String climaGlobal;
    private List<Ambiente> historico;

    private final int worldMapWidth = 21;
    private final int worldMapHeight = 21;

    int[][] worldMapNum;

    int playerMapX;  // these are the coordinates for the player, in the world map
    int playerMapY;

    public EnvironmentManager() {
        this.ambientesDisponiveis = new ArrayList<>();
        this.historico = new ArrayList<>();

        this.playerMapX = 10;
        this.playerMapY = 10;
        this.worldMapNum = new int[worldMapWidth][worldMapHeight];

        loadMap();

        ambientesDisponiveis.add(AmbienteFloresta::new);
        ambientesDisponiveis.add(AmbienteCaverna::new);
        ambientesDisponiveis.add(AmbienteLagoRio::new);
        ambientesDisponiveis.add(AmbienteMontanha::new);
        ambientesDisponiveis.add(AmbienteRuinas::new);
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
        } else if (player.envY > gp.envHeight - gp.tileSize) {
            this.playerMapY++;
            player.envY = 0;
        } else if (player.envY < 0) {
            this.playerMapY--;
            player.envY = gp.envHeight - gp.tileSize;
        }
    }

    public void changeEnvironment(TileManager tileM) {
        switch (this.worldMapNum[playerMapX][playerMapY]) {
            case 0:
                tileM.loadMap("/maps/floresta1.csv");
                break;
            case 1:
                tileM.loadMap("/maps/floresta2.csv");
                break;
        }

    }

    /* seleciona um novo ambiente e checa se ele n eh igual ao ultimo */
    public Ambiente mudarAmbiente() {
        Random random = new Random();

        int numero;

        Ambiente novoAmbiente;

        // qnd o historico estiver vazio, o ultimo ambiente sera null, dps sera sempre o ultimo membro da lista
        Ambiente ultimoAmbiente = historico.isEmpty() ? null : historico.getLast();

        do {
            numero = random.nextInt(ambientesDisponiveis.size());
            novoAmbiente = ambientesDisponiveis.get(numero).get();
        } while (ultimoAmbiente != null && novoAmbiente.getClass().equals(ultimoAmbiente.getClass()));

        historico.add(novoAmbiente);

        // limita o historico a apenas os ultimos dois jogo.ambientes visitados
        // quando passa disso ele remove o primeiro elemento da lista
        if (historico.size() > 2) {
            historico.removeFirst();
        }

        return novoAmbiente;
    }

    public void setClimaGlobal(Ambiente ambiente) {
        this.climaGlobal = ambiente.getClimaAtual();
    }

    public String getClimaGlobal() {
        return this.climaGlobal;
    }
}
