package game.events.eventoClimatico;

import game.events.Event;

public abstract class ClimateEvent extends Event {
    private String tipoDeClima;
    private int duracao;
    private String efeitoAmbiente;

    public ClimateEvent(String nome, String descricao, String tipoDeClima, int duracao, String efeitoAmbiente) {
        super(nome, descricao, 0.02);
        this.tipoDeClima = tipoDeClima;
        this.duracao = duracao;
        this.efeitoAmbiente = efeitoAmbiente;
    }
}
