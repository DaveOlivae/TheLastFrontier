package jogo.eventos.eventoDoencaFerimento;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

public class Infeccao extends EventoDoencaFerimento{
    public Infeccao() {
        super("Infecção",
                "Você está se sentido doente, melhor tomar remédio.",
                "Infecção",
                "Vida, Energia",
                "Remédio");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
