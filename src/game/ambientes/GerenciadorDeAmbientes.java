package game.ambientes;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GerenciadorDeAmbientes {
    private List<Supplier<Ambiente>> ambientesDisponiveis;
    private String climaGlobal;
    private List<Ambiente> historico;

    public GerenciadorDeAmbientes() {
        this.ambientesDisponiveis = new ArrayList<>();
        this.historico = new ArrayList<>();

        ambientesDisponiveis.add(AmbienteFloresta::new);
        ambientesDisponiveis.add(AmbienteCaverna::new);
        ambientesDisponiveis.add(AmbienteLagoRio::new);
        ambientesDisponiveis.add(AmbienteMontanha::new);
        ambientesDisponiveis.add(AmbienteRuinas::new);
    }

    /* seleciona um novo ambiente e checa se ele n eh igual ao ultimo */
    public Ambiente mudarAmbiente() {
        Random random = new Random();

        int numero;

        Ambiente novoAmbiente;

        // qnd o historico estiver vazio, o ultimo ambiente sera null, dps sera sempre o ultimo membro da lista
        Ambiente ultimoAmbiente = historico.isEmpty() ? null : historico.getLast();

        do {
            numero = random.nextInt(ambientesDisponiveis.size());
            novoAmbiente = ambientesDisponiveis.get(numero).get();
        } while (ultimoAmbiente != null && novoAmbiente.getClass().equals(ultimoAmbiente.getClass()));

        historico.add(novoAmbiente);

        // limita o historico a apenas os ultimos dois jogo.ambientes visitados
        // quando passa disso ele remove o primeiro elemento da lista
        if (historico.size() > 2) {
            historico.removeFirst();
        }

        return novoAmbiente;
    }

    public void setClimaGlobal(Ambiente ambiente) {
        this.climaGlobal = ambiente.getClimaAtual();
    }

    public String getClimaGlobal() {
        return this.climaGlobal;
    }
}
