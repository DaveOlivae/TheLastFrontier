package jogo.eventos.eventoDoencaFerimento;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

public class Desidratacao extends EventoDoencaFerimento{
    public Desidratacao() {
        super("Desidratação",
                "Você está desidratado, beba água imediatamente.",
                "Desidratação",
                "Energia",
                "Água");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
