package itens.remedios;

import itens.Item;
import personagens.Personagem;

public class Remedio extends Item {
    private String tipo;
    private String efeito;

    public Remedio(String nome, int peso, int durabilidade, String tipo, String efeito) {
        super(nome, peso, durabilidade, false);
        this.tipo = tipo;
        this.efeito = efeito;
    }

    @Override
    public void usar(Personagem jogador) {

    }

    @Override
    public void getAttributes() {

    }
}
