package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.ambientes.AmbienteFloresta;
import jogo.ambientes.AmbienteLagoRio;
import jogo.ambientes.AmbienteMontanha;
import jogo.eventos.eventoClimatico.ChuvaForte;
import jogo.eventos.eventoClimatico.Nevasca;
import jogo.eventos.eventoCriatura.Lobo;
import jogo.eventos.eventoDoencaFerimento.Desidratacao;
import jogo.eventos.eventoDoencaFerimento.Hipotermia;
import jogo.personagens.Personagem;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

public class GerenciadorDeEventos {
    // nao sei se isso eh uma boa pratica, posso mudar dps
    private List<Evento> historico;
    private Map<Supplier<Evento>, Double> eventosPossiveis;

    public GerenciadorDeEventos() {
        this.historico = new ArrayList<>();
        this.eventosPossiveis = new HashMap<>();
    }

    public void atualizarListaDeEventos(Ambiente ambiente, Personagem jogador) {
        /*
        * esse metodo serve pra atualizar os jogo.eventos possiveis em cada turno
        * */

        // a lista dos jogo.eventos vai ser limpa e atualizada cada vez que o metodo for chamado
        this.eventosPossiveis.clear();

        // jogo.eventos relacionados ao ambiente/clima
        if (ambiente instanceof AmbienteFloresta) {
            this.eventosPossiveis.put(Lobo::new, 0.3);
            if (ambiente.getClimaAtual().equals("Chuva")) {
                this.eventosPossiveis.put(ChuvaForte::new, 0.5);
            }
        } else if (ambiente instanceof AmbienteLagoRio) {
            // aqui eu to comparando o clima atual com a string Chuva, nao sei se isso eh uma boa pratica
            if (ambiente.getClimaAtual().equals("Chuva")) {
                this.eventosPossiveis.put(ChuvaForte::new, 0.5);
            }
        } else if (ambiente instanceof AmbienteMontanha) {
            if (ambiente.getClimaAtual().equals("Nevando")) {
                this.eventosPossiveis.put(Nevasca::new, 0.6);
            }
        }

        // jogo.eventos relacionados ao jogador
        if (jogador.getSede() >= 80) {
            this.eventosPossiveis.put(Desidratacao::new, 0.9);
        }

        if (jogador.eventoNaLista(Nevasca.class) || jogador.eventoNaLista(ChuvaForte.class)) {
            this.eventosPossiveis.put(Hipotermia::new, 0.95);
        }

        for (double e: this.eventosPossiveis.values()) {
            System.out.println(e);
        }
    }

    public void sortearEvento() {

    }
}
