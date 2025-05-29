package game.itens.weapons;

public class Revolver extends Weapon {
    private final int capacidade = 6;
    private int municao = 6;

    public Revolver() {
        super("Revólver", 2, 20, "Arma de fogo", 40, 5);
    }
}
