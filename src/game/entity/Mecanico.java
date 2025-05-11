package game.entity;

import game.graphics.GamePanel;
import game.input.KeyHandler;

public class Mecanico extends Player {
    public Mecanico(String nome, GamePanel gp, KeyHandler keyH) {
        super(nome, 100, 0, 0, 100, 100, 30,
                20, 1, 1, gp, keyH);
    }

    public void kitInicial() {
        super.kitInicial();
    }
}
