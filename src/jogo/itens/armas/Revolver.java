package jogo.itens.armas;

import jogo.personagens.Personagem;

public class Revolver extends Arma{
    private final int capacidade = 6;
    private int municao = 6;

    public Revolver() {
        super("Revólver", 2, 20, "Arma de fogo", 40, 5);
    }

    public void usar(Personagem inimigo) {
        // TODO implementar atirar no inimigo
        municao--;
    }

    @Override
    public void getAttributes() {
        super.getAttributes();
        System.out.printf("Munição: %d/%d%n", this.capacidade, this.municao);
    }

    public int getMunicao() {
        return this.municao;
    }

    public int getCapacidade() {
        return this.capacidade;
    }
}
