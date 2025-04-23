package jogo.itens.armas;


import jogo.personagens.Personagem;

public class Explosivo extends Arma {
    public Explosivo(String nome, int peso, int durabilidade, String tipo, int dano, int alcance) {
        super(nome, peso, durabilidade, tipo, dano, alcance);
    }

    @Override
    public void usar(Personagem jogador) {

    }
}
