package game.eventos;

import game.ambientes.Environment;
import game.ambientes.Forest;
import game.ambientes.LakeRiver;
import game.ambientes.Mountain;
import game.eventos.eventoClimatico.ChuvaForte;
import game.eventos.eventoClimatico.Nevasca;
import game.eventos.eventoCriatura.Cobra;
import game.eventos.eventoCriatura.Lobo;
import game.eventos.eventoDoencaFerimento.Desidratacao;
import game.eventos.eventoDoencaFerimento.Hipotermia;
import game.entity.Player;

import java.util.*;
import java.util.function.Supplier;

public class EventManager {
    // nao sei se isso eh uma boa pratica, posso mudar dps
    private List<Evento> historico;
    private Map<Supplier<Evento>, Double> eventosPossiveis;

    public EventManager() {
        this.historico = new ArrayList<>();
        this.eventosPossiveis = new HashMap<>();
    }

}
