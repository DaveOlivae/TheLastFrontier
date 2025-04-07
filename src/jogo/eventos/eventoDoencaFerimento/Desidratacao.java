package jogo.eventos.eventoDoencaFerimento;

import jogo.ambientes.Ambiente;
import jogo.personagens.Personagem;

public class Desidratacao extends EventoDoencaFerimento{
    public Desidratacao() {
        super("Desidratação",
                "Você está desidratado, beba água imediatamente.",
                "Desidratação",
                "Energia",
                "Água");
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {

    }

    public void ativar() {
        super.ativar();
    }
}
