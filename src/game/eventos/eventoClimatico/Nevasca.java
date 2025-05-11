package game.eventos.eventoClimatico;

import game.ambientes.Ambiente;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Nevasca extends EventoClimatico{
    public Nevasca() {
        super("Nevasca",
                "Uma forte tempestade de neve",
                "Nevasca",
                3,
                "Frio");
    }

    @Override
    public void executar(Player jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.AMBIENTE;
    }
}
