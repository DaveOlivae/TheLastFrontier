package eventos.eventoClimatico;

import eventos.Evento;

public abstract class EventoClimatico extends Evento {
    private String tipoDeClima;
    private int duracao;
    private String efeitoAmbiente;

    public EventoClimatico(String nome, String descricao, int probOcorrencia, String[] impacto, boolean condAtivacao,
                           String tipoDeClima, int duracao, String efeitoAmbiente) {
        super(nome, descricao, probOcorrencia, impacto, condAtivacao);
        this.tipoDeClima = tipoDeClima;
        this.duracao = duracao;
        this.efeitoAmbiente = efeitoAmbiente;
    }
}
