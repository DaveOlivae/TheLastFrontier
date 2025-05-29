package game.eventos.eventoClimatico;

import game.ambientes.Environment;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class CalorExtremo extends EventoClimatico{
    public CalorExtremo() {
        super("Calor Extremo",
                "O calor está insuportável, você está ficando com mais sede",
                "Quente",
                3,
                "Sede");
    }

    @Override
    public void executar(Player jogador, Environment local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.AMBIENTE;
    }
}
