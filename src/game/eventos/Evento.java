package game.eventos;

import game.ambientes.Environment;
import game.entity.Player;

public abstract class Evento {
    private String nome;
    private String descricao;

    public Evento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public abstract void executar(Player jogador, Environment local);

    public abstract AlvoDoEvento getAlvoDoEvento();

    public String getNome() {
        return this.nome;
    }
}
