package game.eventos.eventoCriatura;

import game.ambientes.Environment;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Cobra extends EventoCriatura{
    public Cobra() {
        super("Ataque de Cobra",
                "Uma cobra te atacou furtivamente! Você está envenenado!",
                "Cobra",
                5,
                new String[]{"Nada"});
    }

    @Override
    public void executar(Player jogador, Environment local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
