package itens;

public class Agua extends Item{
    private int pureza;
    private int volume;

    public Agua(String nome, int peso, int durabilidade, int pureza, int volume) {
        super(nome, peso, durabilidade);
        this.pureza = pureza;
        this.volume = volume;
    }
}
