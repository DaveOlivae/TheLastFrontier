package jogo.itens.alimentos;

import jogo.personagens.Personagem;

public class Fruta extends Alimento{
    public Fruta(String nome) {
        super(nome, 1, 1, 10, "Fruta", 5);
    }

    public void usar(Personagem jogador) {
        jogador.restaurarFome(getValorNutricional());
    }
}
