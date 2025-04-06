package eventos;

import ambientes.Ambiente;
import personagens.Personagem;

public class EventoCriatura extends Evento{
    private String tipoDeCriatura;
    private int nivelPerigo;
    private String[] opcoesDeAcao;

    public EventoCriatura(String nome, String descricao, int probOcorrencia, String[] impacto, boolean condAtivacao,
                          String tipoDeCriatura, int nivelPerigo, String[] opcoesDeAcao) {
        super(nome, descricao, probOcorrencia, impacto, condAtivacao);
        this.tipoDeCriatura = tipoDeCriatura;
        this.nivelPerigo = nivelPerigo;
        this.opcoesDeAcao = opcoesDeAcao;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }
}
