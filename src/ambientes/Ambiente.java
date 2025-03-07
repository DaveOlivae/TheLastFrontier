package ambientes;

import itens.*;
import personagens.*;
import java.util.List;
import java.util.ArrayList;

public class Ambiente {
    // atributos
    private String nome;
    private String descricao;
    private int dificuldadeExploracao;
    private String clima;
    private List<Item> recursos;

    // construtor
    public Ambiente(String nome, String descricao, int dificuldadeExploracao, String clima) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.clima = clima;
        this.recursos = new ArrayList<>();
        adicionarRecursos();
    }

    // metodos
    public void adicionarRecursos() {}

    public void adicionarItem(Item item) {
        recursos.add(item);
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

    // gets
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
