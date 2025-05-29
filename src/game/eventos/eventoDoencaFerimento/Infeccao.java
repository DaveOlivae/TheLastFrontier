package game.eventos.eventoDoencaFerimento;

import game.ambientes.Environment;
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
    public void executar(Player jogador, Environment local) {

    }

    @Override
    public AlvoDoEvento getAlvoDoEvento() {
        return AlvoDoEvento.JOGADOR;
    }
}
