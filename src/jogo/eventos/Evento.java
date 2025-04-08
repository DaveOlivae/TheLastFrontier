package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.personagens.Personagem;

public abstract class Evento {
    private String nome;
    private String descricao;

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public abstract void executar(Personagem jogador, Ambiente local);

    public abstract AlvoDoEvento getAlvoDoEvento();

    public String getNome() {
        return this.nome;
    }
}
