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

    public Ambiente mudarAmbiente() {
        Random random = new Random();

        int numero = random.nextInt(5);

        Ambiente novoAmbiente;

        // seleciona um novo ambiente e checa se ele n eh igual ao ultimo
        while(true) {
            novoAmbiente = ambientesDisponiveis[numero];

            if(!historico.isEmpty()) {
                if(!novoAmbiente.equals(historico.getLast())) {
                    break;
                }
            } else {
                break;
            }
        }

        this.climaGlobal = novoAmbiente.getClima();

        historico.add(novoAmbiente);

        return novoAmbiente;
    }

    public String getClimaGlobal() {
        return this.climaGlobal;
    }
}
