package game.eventos.eventoDoencaFerimento;

import game.ambientes.Environment;
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
    public void executar(Player jogador, Environment local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
