package ambientes;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeAmbientes {
    private Ambiente[] ambientesDisponiveis;
    private String climaGlobal;
    private List<Ambiente> historico;

    public GerenciadorDeAmbientes() {
        historico = new ArrayList<>();

        ambientesDisponiveis = new Ambiente[] {new AmbienteCaverna(), new AmbienteFloresta(), new AmbienteRuinas(),
        new AmbienteMontanha(), new AmbienteLagoRio()};
    }

    /* seleciona um novo ambiente e checa se ele n eh igual ao ultimo */
    public Ambiente mudarAmbiente() {
        Random random = new Random();

        int numero;

        Ambiente novoAmbiente;

        // qnd o historico estiver vazio, o ultimo ambiente sera null, dps sera sempre o ultimo membro da lista
        Ambiente ultimoAmbiente = historico.isEmpty() ? null : historico.getLast();

        do {
            numero = random.nextInt(5);
            novoAmbiente = ambientesDisponiveis[numero];
        } while (novoAmbiente.equals(ultimoAmbiente));

        historico.add(novoAmbiente);

        // limita o historico a apenas os ultimos dois ambientes visitados
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
