package ambientes;

public class AmbienteLagoRio extends Ambiente{

    public AmbienteLagoRio() {
        super("Lago e Rio",
                "Uma planície em que um rio desagua num grande lago.",
                25,
                "úmido",
                new String[] {"Peixe", "Algas", "Água", "Fibra"});
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
