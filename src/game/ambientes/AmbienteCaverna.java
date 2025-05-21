package game.ambientes;

import game.graphics.TileManager;
import game.itens.Item;
import game.itens.alimentos.Agua;
import game.itens.armas.Explosivo;
import game.itens.armas.Revolver;
import game.itens.ferramentas.Ferramenta;
import game.itens.ferramentas.Lanterna;
import game.itens.ferramentas.Picareta;
import game.itens.ferramentas.Tocha;
import game.itens.materiais.*;
import game.itens.remedios.Curativo;
import game.entity.Player;

import java.util.Scanner;

public class AmbienteCaverna extends Ambiente {

    public AmbienteCaverna(TileManager tileM) {
        super("Caverna",
                "Um ambiente subterrâneo e muito escuro.",
                18, tileM);
    }

    public void carregarAmbiente(String path) {
        super.carregarAmbiente(path);
    }

    public void atualizarClimas() {
        adicionarClima("Úmido");
        adicionarClima("Chuva");
    }

    public void adicionarRecursos() {
        adicionarRecurso(new MinerioFerro(), 6);
        adicionarRecurso(new MinerioCobre(), 4);
        adicionarRecurso(new Carvao(), 2);
        adicionarRecurso(new Ouro(), 13);
        adicionarRecurso(new Diamante(), 17);
    }

    public void adicionarItens() {

    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public String getName() {
        return super.getName();
    }

    // esse metodo verifica se o jogador tem uma tocha ou lanterna equipada, se sim, ele diminui a dificuldade de exploracao
    public int getDificuldadeExploracao(Player jogador) {
        if (jogador.getItemEquipado() instanceof Tocha || jogador.getItemEquipado() instanceof Lanterna) {
            Ferramenta ferramenta = (Ferramenta) jogador.getItemEquipado();  // downcasting pra acessar o metodo geteficiencia

            System.out.printf("Sua %s ilumina a caverna%n", ferramenta.getName());
            return super.getDificuldadeExploracao() + ferramenta.getEficiencia();
        } else {
            return super.getDificuldadeExploracao();
        }
    }
}
