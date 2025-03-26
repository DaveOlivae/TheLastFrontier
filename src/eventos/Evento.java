package eventos;

import ambientes.Ambiente;
import personagens.Personagem;

public abstract class Evento {
    private String nome;
    private String descricao;
    private int probOcorrencia;
    private String[] impacto;
    private boolean condAtivacao;

    public Evento(String nome, String descricao, int probOcorrencia, String[] impacto, boolean condAtivacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.probOcorrencia = probOcorrencia;
        this.impacto = impacto;
        this.condAtivacao = condAtivacao;
    }

    public void executar(Personagem jogador, Ambiente local) {}
}
