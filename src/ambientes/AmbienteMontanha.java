package ambientes;

public class AmbienteMontanha extends Ambiente{
    public AmbienteMontanha() {
        super("Montanhas",
                "Um ambiente de difícil travessia",
                75,
                "seco",
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
