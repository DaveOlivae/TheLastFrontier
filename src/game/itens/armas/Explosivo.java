package game.itens.armas;


import game.entity.Player;

public class Explosivo extends Arma {
    public Explosivo(String nome, int peso, int durabilidade, String tipo, int dano, int alcance) {
        super(nome, peso, durabilidade, tipo, dano, alcance);
    }

    @Override
    public void usar(Player jogador) {

    }
}
