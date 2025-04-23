package jogo.eventos.eventoCriatura;

import jogo.eventos.Evento;

public abstract class EventoCriatura extends Evento {
    private String tipoDeCriatura;
    private int nivelPerigo;
    private String[] opcoesDeAcao;

    public EventoCriatura(String nome, String descricao, String tipoDeCriatura,
                          int nivelPerigo, String[] opcoesDeAcao) {
        super(nome, descricao);
        this.tipoDeCriatura = tipoDeCriatura;
        this.nivelPerigo = nivelPerigo;
        this.opcoesDeAcao = opcoesDeAcao;
    }

    public String[] getOpcoesDeAcao() {
        return this.opcoesDeAcao;
    }
}
