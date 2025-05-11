package game.eventos.eventoDoencaFerimento;

import game.ambientes.Ambiente;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Hipotermia extends EventoDoencaFerimento{
    public Hipotermia() {
        super("Hipotermia",
                "Você está com hipotermia — seu corpo está perdendo " +
                "calor mais rápido do que consegue produzir. Você perderá vida até se aquecer.",
                "Hipotermia",
                "Vida",
                "Abrigo");
    }

    @Override
    public void executar(Player jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
