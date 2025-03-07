package ambientes;

// TODO : resover encontro de itens nos ambientes

import itens.*;
import java.util.List;
import java.util.ArrayList;

public class AmbienteCaverna extends Ambiente{

    public AmbienteCaverna() {
        super("Caverna",
                "Um ambiente subterrâneo e muito escuro.",
                100,
                "úmido");
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
