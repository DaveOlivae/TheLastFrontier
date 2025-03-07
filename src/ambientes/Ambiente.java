package ambientes;

import personagens.*;

public class Ambiente {
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private String clima;
    private String[] recursos;

    public Ambiente(String nome, String descricao, int dificuldadeExploracao, String clima, String[] recursos) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.clima = clima;
        this.recursos = recursos;
    }

    public void informacao() {
        System.out.printf("Você chegou num ambiente de %s.%n", this.nome);
        System.out.printf("%s%n", this.descricao);
    }

    public void explorar(Personagem jogador) {

    }

    public void gerarEvento() {

    }

    public void modificarClima() {

    }

    public String getName() {
        return this.nome;
    }

    public String getClima() {
        return this.clima;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
