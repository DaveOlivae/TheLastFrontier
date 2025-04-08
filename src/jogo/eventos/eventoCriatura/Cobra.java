package jogo.eventos.eventoCriatura;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

public class Cobra extends EventoCriatura{
    public Cobra() {
        super("Ataque de Cobra",
                "Uma cobra te atacou furtivamente! Você está envenenado!",
                "Cobra",
                5,
                new String[]{"Nada"});
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
