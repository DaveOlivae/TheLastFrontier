package game;

import game.eventos.Evento;

public interface LidarComEventos {

    boolean eventoNaLista(Class<? extends Evento> tipo);

    void adicionarEvento(Evento evento);

    void removerEvento(Class<? extends Evento> tipo);
}
