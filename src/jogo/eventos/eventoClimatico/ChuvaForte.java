package jogo.eventos.eventoClimatico;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

public class ChuvaForte extends EventoClimatico{
    public ChuvaForte() {
        super("Chuva Forte",
                "Está caindo uma chuva torrencial, melhor encontrar abrigo",
                "Úmido",
                4,
                "Exploração");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.AMBIENTE;
    }
}
