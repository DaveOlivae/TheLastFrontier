package game.eventos.eventoClimatico;

import game.ambientes.Environment;
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
    public void executar(Player jogador, Environment local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.AMBIENTE;
    }
}
