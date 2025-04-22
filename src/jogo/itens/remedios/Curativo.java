package jogo.itens.remedios;

import jogo.personagens.Personagem;

public class Curativo extends Remedio{
    public Curativo() {
        super("Curativo", 1, 1, "Bandagem", "Cura 20 de vida");
    }

    @Override
    public void usar(Personagem jogador) {
        jogador.attVida(20);
    }
}
