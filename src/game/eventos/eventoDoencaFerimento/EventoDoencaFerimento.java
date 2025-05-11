package game.eventos.eventoDoencaFerimento;

import game.eventos.Evento;

public abstract class EventoDoencaFerimento extends Evento {
    private String tipoCondicao;
    private String impacto;
    private String curaDisponivel;

    public EventoDoencaFerimento(String nome, String descricao, String tipoCondicao,
                                 String impacto, String curaDisponivel) {
        super(nome, descricao);
        this.tipoCondicao = tipoCondicao;
        this.impacto = impacto;
        this.curaDisponivel = curaDisponivel;
    }
}
