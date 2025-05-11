package game.eventos.eventoDoencaFerimento;

import game.ambientes.Ambiente;
import game.eventos.AlvoDoEvento;
import game.entity.Player;

public class Infeccao extends EventoDoencaFerimento{
    public Infeccao() {
        super("Infecção",
                "Você está se sentido doente, melhor tomar remédio.",
                "Infecção",
                "Vida, Energia",
                "Remédio");
    }

    @Override
    public void executar(Player jogador, Ambiente local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
