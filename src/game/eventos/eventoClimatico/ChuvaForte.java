package game.eventos.eventoClimatico;

import game.ambientes.Environment;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class ChuvaForte extends EventoClimatico{
    public ChuvaForte() {
        super("Chuva Forte",
                "Está caindo uma chuva torrencial, melhor encontrar abrigo",
                "Úmido",
                4,
                "Exploração");
    }

    @Override
    public void executar(Player jogador, Environment local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.AMBIENTE;
    }
}
