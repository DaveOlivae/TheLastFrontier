package jogo.eventos.eventoDoencaFerimento;

import jogo.ambientes.Ambiente;
import jogo.personagens.Personagem;

public class Hipotermia extends EventoDoencaFerimento{
    public Hipotermia() {
        super("Hipotermia",
                "Você está com hipotermia — seu corpo está perdendo " +
                "calor mais rápido do que consegue produzir. Você perderá vida até se aquecer.",
                "Hipotermia",
                "Vida",
                "Abrigo");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    // nao sei se isso ta correto, posso trocar dps
    public void ativar() {
        super.ativar();
    }
}
