package itens;

public class Item {
    private String nome;
    private int peso;
    private int durabilidade;

    public Item(String nome, int peso, int durabilidade) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
    }

    public void usar() {

    }

    public String getNome() {
        return this.nome;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getDurabilidade() {
        return this.durabilidade;
    }
}
