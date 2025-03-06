package ambientes;

public class GerenciadorDeAmbientes {
    private String climaGlobal;
    private String[] historico;

    public Ambiente mudarAmbiente(Ambiente novoAmbiente) {
        novoAmbiente = new AmbienteFloresta();

        this.climaGlobal = novoAmbiente.getClima();

        return novoAmbiente;
    }
}
