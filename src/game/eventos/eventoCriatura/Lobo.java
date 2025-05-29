package game.eventos.eventoCriatura;

import game.ambientes.Environment;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Lobo extends EventoCriatura{
    private int vida = 50;

    public Lobo() {
        super("Lobo",
                "Um lobo selvagem apareceu! Ele não parece estar afim de fazer amigos",
                "Lobo",
                9,
                new String[]{"Atacar", "Usar Item", "Tentar Correr"});
    }

    @Override
    public void executar(Player jogador, Environment local) {
//        // vou ter que criar um scanner aqui mesmo
//        EventoDeAtaque batalha = new EventoDeAtaque();
//
//        batalha.ataque(jogador, this);
    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }

    public int getVida() {
        return this.vida;
    }
}
