package jogo.eventos.eventoClimatico;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

public class CalorExtremo extends EventoClimatico{
    public CalorExtremo() {
        super("Calor Extremo",
                "O calor está insuportável, você está ficando com mais sede",
                "Quente",
                3,
                "Sede");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.AMBIENTE;
    }
}
