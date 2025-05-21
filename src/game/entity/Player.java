package game.entity;

import game.ambientes.Ambiente;
import game.ambientes.GerenciadorDeAmbiente;
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

    public final int screenX;
    public final int screenY;

    private KeyHandler keyH;

    public Player(String nome, int vida, int fome, int sede, int energia, int sanidade, int pesoTotal,
                  int espacoDisponivel, int exploracao, int rastreamento, GamePanel gp, KeyHandler keyH) {
        super(gp, 8, 16, 48, 48, 14 * gp.tileSize, 26 * gp.tileSize, 4, "down");
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

        // config for the player collision rectangle
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);

        this.keyH = keyH;

        getPlayerImage("/sprites/littleguy1.png");

        kitInicial();
    }

    public void update() {

        // when there are no keys being pressed the standard sprite is the one in the index 1
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (this.keyH.upPressed) {
                setDirection("up");
            } else if (this.keyH.downPressed) {
                setDirection("down");
            } else if (keyH.rightPressed) {
                setDirection("right");
            } else {
                setDirection("left");
            }

            /* ------------ check tile/object collision -------------- */

            // tile collision
            setCollisionOn(false);
            gp.getcChecker().checkTile(this);

            // object collision
            int objIndex = gp.getcChecker().checkObject(this, true);

            pegarItem(objIndex);

            // check collision with npcs

            int npcIndex = gp.getcChecker().checkEntity(this, gp.getNPCs());

            interagirNPC(npcIndex);

            // if collision is false, player can move
            if (!getCollisionOn()) {
                int newEnvY = getEnvY();
                int newEnvX = getEnvX();
                switch (getDirection()) {
                    case "up":
                        newEnvY -= getSpeed();
                        break;
                    case "down":
                        newEnvY += getSpeed();
                        break;
                    case "right":
                        newEnvX += getSpeed();
                        break;
                    case "left":
                        newEnvX -= getSpeed();
                        break;
                }
                setEnvY(newEnvY);
                setEnvX(newEnvX);
            }

            /* --------- sprite changer ----------- */

            // every time the sprite counter is over 5, the sprite num will changes by one, the sprite num is the index
            // for the sprites array

            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 5) {
                setSpriteNum(getSpriteNum() + 1);
                setSpriteCounter(0);
            }

            if (getSpriteNum() > 2) {
                setSpriteNum(0);
            }
        } else {
            setSpriteNum(1);
        }

    }

    public void pegarItem(int index) {
        List<Item> itens = gp.getItens();

        if (index != 999) {
            itens.remove(index);
        }
    }

    public void interagirNPC(int index) {
        if (index != 999) {

        }
    }

    public void draw(Graphics2D g2) {

        switch (getDirection()) {
            case "up":
                setImage(up[getSpriteNum()]);
                break;
            case "down":
                setImage(down[getSpriteNum()]);
                break;
            case "right":
                setImage(right[getSpriteNum()]);
                break;
            case "left":
                setImage(left[getSpriteNum()]);
                break;
        }

        int x = screenX;
        int y = screenY;

        if (screenX > getEnvX()) {
            x = getEnvX();
        }
        if (screenY > getEnvY()) {
            y = getEnvY();
        }

        int rightOffset = gp.screenWidth - screenX;

        if (rightOffset > gp.envWidth - getEnvX()) {
            x = gp.screenWidth - (gp.envWidth - getEnvX());
        }

        int bottomOffset = gp.screenHeight - screenY;

        if (bottomOffset > gp.envHeight - getEnvY()) {
            y = gp.screenHeight - (gp.envHeight - getEnvY());
        }

        g2.drawImage(getImage(), x, y, gp.tileSize, gp.tileSize, null);
    }

    /* metodos relacionados aos jogo.eventos */

    public void kitInicial() {
        addItemInventario(new Faca());
        addItemInventario(new Canteen());
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
