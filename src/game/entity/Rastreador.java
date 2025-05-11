package game.entity;

import game.graphics.GamePanel;
import game.input.KeyHandler;
import game.itens.ferramentas.Tocha;

public class Rastreador extends Player {
    public Rastreador(String nome, GamePanel gp, KeyHandler keyH) {
        super(nome, 100, 0, 0, 100, 100, 30,
                20, 2, 3, gp, keyH);

    }

    // a ideia eh que os jogadores vão começar com pelo menos alguns itens
    public void kitInicial() {
        super.kitInicial();
        addItemInventario(new Tocha());
    }
}
