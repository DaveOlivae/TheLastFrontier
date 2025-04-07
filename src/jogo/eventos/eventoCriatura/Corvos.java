package jogo.eventos.eventoCriatura;

import jogo.ambientes.Ambiente;
import jogo.personagens.Personagem;

public class Corvos extends EventoCriatura{
    public Corvos() {
        super("Ataque de Corvos",
                "Uma revoada de Corvos apareceu do nada! Muito estranho...",
                "Corvos",
                0,
                new String[]{"Nada"});
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }
}
