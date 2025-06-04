package game.events.eventoDoencaFerimento;

import game.events.Event;

public abstract class SicknessInjuryEvent extends Event {
    private String tipoCondicao;
    private String impacto;
    private String curaDisponivel;

    public SicknessInjuryEvent(String nome, String descricao, String tipoCondicao,
                               String impacto, String curaDisponivel) {
        super(nome, descricao, 0.1);
        this.tipoCondicao = tipoCondicao;
        this.impacto = impacto;
        this.curaDisponivel = curaDisponivel;
    }
}
