package jogo.eventos.eventoClimatico;

import jogo.eventos.Evento;

public abstract class EventoClimatico extends Evento {
    private String tipoDeClima;
    private int duracao;
    private String efeitoAmbiente;

    public EventoClimatico(String nome, String descricao, String tipoDeClima, int duracao, String efeitoAmbiente) {
        super(nome, descricao);
        this.tipoDeClima = tipoDeClima;
        this.duracao = duracao;
        this.efeitoAmbiente = efeitoAmbiente;
    }
}
