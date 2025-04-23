package jogo.eventos.eventoCriatura;

import jogo.ambientes.Ambiente;
import jogo.eventos.AlvoDoEvento;
import jogo.eventos.EventoDeAtaque;
import jogo.personagens.Personagem;

import java.util.Scanner;

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
    public void executar(Personagem jogador, Ambiente local) {
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
