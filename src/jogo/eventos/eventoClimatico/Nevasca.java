package jogo.eventos.eventoClimatico;

import jogo.ambientes.Ambiente;
import jogo.personagens.Personagem;

public class Nevasca extends EventoClimatico{
    public Nevasca() {
        super("Nevasca",
                "Uma forte tempestade de neve",
                "Nevasca",
                3,
                "Frio");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }
}
