package ambientes;

public class AmbienteFloresta extends Ambiente{
    public AmbienteFloresta() {
        super("Floresta",
                "Um local de vegetação densa e fauna abundante",
                50,
                "úmido",
                new String[] {"Frutas", "Raízes", "Cogumelos", "Madeira"});
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getClima() {
        return super.getClima();
    }
}
