package ambientes;

public class AmbienteCaverna extends Ambiente{

    public AmbienteCaverna() {
        super("Caverna",
                "Um ambiente subterrâneo e muito escuro.",
                100,
                "úmido",
                new String[] {"Minério de ferro", "Ossos de exploradores", "Rocha rara"});
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getClima() {
        return super.getClima();
    }

    public String getName() {
        return super.getName();
    }
}
