package game.eventos;

import game.ambientes.Ambiente;
import game.ambientes.AmbienteFloresta;
import game.ambientes.AmbienteLagoRio;
import game.ambientes.AmbienteMontanha;
import game.eventos.eventoClimatico.ChuvaForte;
import game.eventos.eventoClimatico.Nevasca;
import game.eventos.eventoCriatura.Cobra;
import game.eventos.eventoCriatura.Lobo;
import game.eventos.eventoDoencaFerimento.Desidratacao;
import game.eventos.eventoDoencaFerimento.Hipotermia;
import game.entity.Player;

import java.util.*;
import java.util.function.Supplier;

public class GerenciadorDeEventos {
    // nao sei se isso eh uma boa pratica, posso mudar dps
    private List<Evento> historico;
    private Map<Supplier<Evento>, Double> eventosPossiveis;

    public GerenciadorDeEventos() {
        this.historico = new ArrayList<>();
        this.eventosPossiveis = new HashMap<>();
    }

    public void atualizarListaDeEventos(Ambiente ambiente, Player jogador, boolean caminhar) {
        /*
        * esse metodo serve pra atualizar os eventos possiveis em cada turno
        *
        * eu usei a flag caminhar pra poder definir quais eventos nao podem acontecer e quais podem
        * a ideia eh que se o jogador decidir ir embora do ambiente, quando ele esta indo embora, so
        * eventos de criatura ou alguns de doenca podem acontecer
        * */

        // a lista dos eventos vai ser limpa e atualizada cada vez que o metodo for chamado
        this.eventosPossiveis.clear();

        // jogo.eventos relacionados ao ambiente/clima
        // um evento relacionado ao ambiente nao pode acontecer se o jogador estiver indo embora do ambiente
        if (!caminhar) {
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
        }

        // a ideia eh que ele possa ser atacado quando estiver indo embora
        if (ambiente instanceof AmbienteFloresta) {
            this.eventosPossiveis.put(Lobo::new, 0.3);
            this.eventosPossiveis.put(Cobra::new, 0.3);
        }

        // jogo.eventos relacionados ao jogador
        // TODO ajeitar a qtd maxima de sede antes de perigo de desidratacao
        if (jogador.getSede() >= 80) {
            this.eventosPossiveis.put(Desidratacao::new, 0.9);
        }

        if (jogador.eventoNaLista(Nevasca.class) || jogador.eventoNaLista(ChuvaForte.class)) {
            this.eventosPossiveis.put(Hipotermia::new, 0.95);
        }

        // a ideia eh que sempre tenha 5 por cento de chance de nada acontecer
        this.eventosPossiveis.put(null, 0.05);
    }

    public void sortearEvento(Ambiente ambiente, Player jogador) {

        double probNNorm = 0;  // probabilidade n normalizada

        for (double prob : this.eventosPossiveis.values()) {
            probNNorm += prob;
        }

        double rand = Math.random() * 1;
        double acc = 0.0;

        for (Map.Entry<Supplier<Evento>, Double> entrada : eventosPossiveis.entrySet()) {
            acc += entrada.getValue()/probNNorm;  // aqui vou normalizar a probabilidade

            // se for null ent eh o evento livre ai so pula
            if (rand <= acc) {
                if (entrada.getKey() != null) {
                    Evento evento = entrada.getKey().get();

                    if (evento.getAlvoDoEvento() == AlvoDoEvento.JOGADOR) {
                        jogador.adicionarEvento(evento);
                    } else if (evento.getAlvoDoEvento() == AlvoDoEvento.AMBIENTE) {
                        ambiente.adicionarEvento(evento);
                    }

                    System.out.println("Evento sorteado: " + evento.getNome());
                }
                return;
            }
        }
    }
}
