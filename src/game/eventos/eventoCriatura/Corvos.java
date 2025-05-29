package game.eventos.eventoCriatura;

import game.ambientes.Environment;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Corvos extends EventoCriatura{
    public Corvos() {
        super("Ataque de Corvos",
                "Uma revoada de Corvos apareceu do nada! Muito estranho...",
                "Corvos",
                0,
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
