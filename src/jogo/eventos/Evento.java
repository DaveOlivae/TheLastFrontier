package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.personagens.Personagem;

public abstract class Evento {
    private String nome;
    private String descricao;
    private boolean condAtivacao;

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public abstract void executar(Personagem jogador, Ambiente local);

    public void ativar() {
        this.condAtivacao = true;
    }
}
