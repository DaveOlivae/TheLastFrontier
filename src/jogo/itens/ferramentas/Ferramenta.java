package jogo.itens.ferramentas;

import jogo.itens.Item;
import jogo.personagens.Personagem;

public class Ferramenta extends Item {
    private String tipo;
    private int eficiencia;

    public Ferramenta(String nome, int peso, int durabilidade, String tipo, int eficiencia) {
        super(nome, peso, durabilidade, true);
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }

    @Override
    public void usar(Personagem jogador) {

    }

    @Override
    public void getAttributes() {

    }

    public int getEficiencia() {
        return this.eficiencia;
    }
}
