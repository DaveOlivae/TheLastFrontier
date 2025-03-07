package ambientes;

public class AmbienteRuinas extends Ambiente{
    public AmbienteRuinas() {
        super("Ruinas",
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
