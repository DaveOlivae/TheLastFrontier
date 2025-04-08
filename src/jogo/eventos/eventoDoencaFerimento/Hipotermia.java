package jogo.eventos.eventoDoencaFerimento;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

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
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
