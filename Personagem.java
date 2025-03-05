public class Personagem {

    protected String nome;
    protected int vida;
    protected int fome;
    protected int sede;
    protected int energia;
    protected int sanidade;
    private Inventario inventario;

    public void setName(String nome) {
        this.nome = nome;
    } 

    public String getName() {
        return this.nome;
    }

    public int getVida() {
        return this.vida;
    }

    public int getFome() {
        return this.fome;
    }

    public int getSede() {
        return this.sede;
    }

    public int getEnergia() {
        return this.energia;
    }

    public int getSanidade() {
        return this.sanidade;
    }
}