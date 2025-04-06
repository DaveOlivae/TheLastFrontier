package itens.alimentos;

import itens.Item;
import personagens.Personagem;

public class Agua extends Item {
    private int pureza;
    private int volume;

    public Agua(int peso, int pureza, int volume) {
        super("Água", peso, 1, false);
        this.pureza = pureza;
        this.volume = volume;
    }

    @Override
    public void usar(Personagem jogador) {

    }

    public void getAttributes() {
        System.out.printf("- Nome: %s%n- Peso: %s%n- Pureza: %s%n", getNome(), getPeso(), getPureza());
    }

    public int getPureza() {
        return this.pureza;
    }
}
