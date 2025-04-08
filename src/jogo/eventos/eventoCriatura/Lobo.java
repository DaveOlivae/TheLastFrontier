package jogo.eventos.eventoCriatura;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.personagens.Personagem;

public class Lobo extends EventoCriatura{
    public Lobo() {
        super("Lobo",
                "Um lobo selvagem apareceu! Ele não parece estar afim de fazer amigos",
                "Lobo",
                9,
                new String[]{"Atacar", "Tentar Correr"});
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
