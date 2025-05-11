package game.entity;

import game.eventos.Evento;
import game.graphics.GamePanel;
import game.graphics.SpriteSheet;
import game.input.KeyHandler;
import game.itens.*;

import game.LidarComEventos;
import game.itens.armas.Faca;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player extends Entity implements LidarComEventos {
    private String nome;
    private int vida;
    private int fome;
    private int sede;
    private int energia;
    private int sanidade;
    private int exploracao;
    private int rastreamento;
    private Inventario inventario;
    private Item itemEquipado;
    private List<Evento> eventosAtivos;  // essa lista vai guardar os jogo.eventos ativos relacionados ao personagem
    private boolean protegido; // fiz esse atributo pra caso o jogador encontre abrigo não precisar passar por
                                // evento aleatorio

    GamePanel gp;
    KeyHandler keyH;

    public Player(String nome, int vida, int fome, int sede, int energia, int sanidade, int pesoTotal,
                  int espacoDisponivel, int exploracao, int rastreamento, GamePanel gp, KeyHandler keyH) {
        this.nome = nome;
        this.vida = vida;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
        this.sanidade = sanidade;
        this.inventario = new Inventario(pesoTotal, espacoDisponivel);
        this.exploracao = exploracao;  // exploracao eh habilidade de explorar e vai de 1 a 20
        this.rastreamento = rastreamento; // tbm vai de 1 a 20
        this.protegido = false; // o jogador vai comecar nao estando protegido
        this.eventosAtivos = new ArrayList<>();

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();

        getPlayerImage();

        kitInicial();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        SpriteSheet spriteSheetUp = new SpriteSheet("/sprites/player/up.png");

        for (int i = 0; i < 12; i++) {
            up[i] = spriteSheetUp.getFrame(i * 24, 0, 24, 24);
        }

        SpriteSheet spriteSheetDown = new SpriteSheet("/sprites/player/down.png");

        for (int i = 0; i < 12; i++) {
            down[i] = spriteSheetDown.getFrame(i * 24, 0, 24, 24);
        }

        SpriteSheet spriteSheetRight = new SpriteSheet("/sprites/player/right.png");

        for (int i = 0; i < 12; i++) {
            right[i] = spriteSheetRight.getFrame(i * 24, 0, 24, 24);
        }

        SpriteSheet spriteSheetLeft = new SpriteSheet("/sprites/player/left.png");

        for (int i = 0; i < 12; i++) {
            left[i] = spriteSheetLeft.getFrame(i * 24, 0, 24, 24);
        }
    }

    public void update() {
        // the way that this is implemented you can move diagonally, im not sure if im gonna keep this

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (this.keyH.upPressed) {
                y -= speed;
                direction = "up";
            } else if (this.keyH.downPressed) {
                y += speed;
                direction = "down";
            }

            if (keyH.rightPressed) {
                x += speed;
                direction = "right";
            } else if (keyH.leftPressed) {
                x -= speed;
                direction = "left";
            }

            ++spriteCounter;
            if (spriteCounter > 3) {
                ++spriteNum;
                spriteCounter = 0;
            }

            if (spriteNum > 11) {
                spriteNum = 0;
            }
        } else {
            spriteNum = 0;
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up[spriteNum];
                break;
            case "down":
                image = down[spriteNum];
                break;
            case "right":
                image = right[spriteNum];
                break;
            case "left":
                image = left[spriteNum];
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    /* metodos relacionados aos jogo.eventos */

    public void kitInicial() {
        addItemInventario(new Faca());
        addItemInventario(new Cantil());
    }

    @Override
    public void adicionarEvento(Evento evento) {
        this.eventosAtivos.add(evento);
    }

    @Override
    public boolean eventoNaLista(Class<? extends Evento> tipo) {
        for (Evento e : this.eventosAtivos) {
            if (tipo.isInstance(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removerEvento(Class<? extends Evento> tipo) {
    }

    /* metodos relacionados aos itens e inventario */

    public void acessarInventario(Scanner input) {
        this.inventario.acessarInventario(input, this);
    }

    public void mostrarInventario() {
        this.inventario.mostrarItens(this);
    }

    public boolean playerInvEmpty() {
        return this.inventario.emptyInventory();
    }

    public void addItemInventario(Item item) {
        this.inventario.adicionarItem(item);
    }

    public void remvItemInventario(int i) {
        this.inventario.removerItem(i);
    }

    public void inspecionarItem(int i) {
        this.inventario.inspecionarItem(i);
    }

    public void usarItem(int i, Player jogador) {
        inventario.usarItem(i, jogador);
    }

    public void equiparItem(Item item) {
        this.itemEquipado = item;
    }

    /* metodos relacionados aos atributos*/

    public void attFomeSede() {
        this.fome += 3;
        this.sede += 4;  // sede maior que a fome
    }

    public void attEnergia(int pontos) {
        if ((this.energia += pontos) <= 0) {
            this.energia = 0;
        }
    }

    public void attVida(int pontos) {

    }

    public void restaurarFome(int pontos) {
        if((this.fome -= pontos) < 0) {
            this.fome = 0;
        }
    }

    public void restaurarSede(int pontos) {
        if((this.sede -= pontos) < 0) {
            this.sede = 0;
        }
    }

    public void diminuirVida(int valor) {
        this.vida -= valor;
    }

    public void showAttributes() {
        System.out.printf("- Vida: %d%n" +
                        "- Fome: %d%n" +
                        "- Sede: %d%n" +
                        "- Energia: %d%n" +
                        "- Sanidade: %d%n" +
                        "- Peso Total: %d/%d%n" +
                        "- Espaço Total: %d/%d%n",
                getVida(), getFome(), getSede(), getEnergia(), getSanidade(),
                inventario.getPeso(), inventario.getPesoTotal(),
                inventario.getEspaco(), inventario.getPesoTotal());
    }

    public void estaProtegido() {
        this.protegido = true;
    }

    // gets e sets

    public int getExploracao() {
        return this.exploracao;
    }

    public Item getItemEquipado() {
        return this.itemEquipado;
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

    public int getRastreamento() {
        return this.rastreamento;
    }

    public int getInvPeso() {
        return this.inventario.getPeso();
    }

    public int getInvEspaco() {
        return this.inventario.getEspaco();
    }

    public int getInvPesoTot() {
        return this.inventario.getPesoTotal();
    }

    public int getInvEspDisp() {
        return this.inventario.getEspacoDisponivel();
    }
}
