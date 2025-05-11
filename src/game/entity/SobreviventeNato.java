package game.entity;

import game.graphics.GamePanel;
import game.input.KeyHandler;

public class SobreviventeNato extends Player {
    public SobreviventeNato(String nome, GamePanel gp, KeyHandler keyH) {
        super(nome, 100, 0, 0, 100, 100,
                30, 20, 3, 2, gp, keyH);
    }

    public void kitInicial() {
        super.kitInicial();
    }
}
