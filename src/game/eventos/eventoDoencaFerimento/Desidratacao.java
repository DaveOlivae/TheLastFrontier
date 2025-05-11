package game.eventos.eventoDoencaFerimento;

import game.ambientes.Ambiente;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Desidratacao extends EventoDoencaFerimento{
    public Desidratacao() {
        super("Desidratação",
                "Você está desidratado, beba água imediatamente.",
                "Desidratação",
                "Energia",
                "Água");
    }

    @Override
    public void executar(Player jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
