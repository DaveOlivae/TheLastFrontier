public class Personagem {

    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;

    public Personagem(String nome, int vida, int fome, int sede, int energia, int sanidade) {
        this.nome = nome;
        this.vida = vida;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
        this.sanidade = sanidade;
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