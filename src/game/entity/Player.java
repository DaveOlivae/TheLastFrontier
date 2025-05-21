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
        super(gp);
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

        this.solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 48;
        solidArea.height = 48;

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);

        this.keyH = keyH;

        setDefaultValues();

        getPlayerImage();

        kitInicial();
    }

    public void setDefaultValues() {

        envX = 14 * gp.tileSize;  // player starting position
        envY = 26 * gp.tileSize;
        speed = 4;
        direction = "down";  // player starts facing down
    }

    public void getPlayerImage() {
        // this method loads/crops the sprites of the player

        SpriteSheet spriteSheet = new SpriteSheet("/sprites/player/01-generic.png");

        for (int i = 0; i < 3; i++) {
            up[i] = spriteSheet.getFrame(i * 16, 48, 16, 16);
        }

        for (int i = 0; i < 3; i++) {
            down[i] = spriteSheet.getFrame(i * 16, 0, 16, 16);
        }

        for (int i = 0; i < 3; i++) {
            right[i] = spriteSheet.getFrame(i * 16, 32, 16, 16);
        }

        for (int i = 0; i < 3; i++) {
            left[i] = spriteSheet.getFrame(i * 16, 16, 16, 16);
        }
    }

    public void update() {

        // when there are no keys being pressed the standard sprite is the one in the index 1
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (this.keyH.upPressed) {
                direction = "up";
            } else if (this.keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else {
                direction = "left";
            }

            /* ------------ check tile/object collision -------------- */

            // tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // object collision
            int objIndex = gp.cChecker.checkObject(this, true);

            pegarItem(objIndex);

            // if collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        envY -= speed;
                        break;
                    case "down":
                        envY += speed;
                        break;
                    case "right":
                        envX += speed;
                        break;
                    case "left":
                        envX -= speed;
                        break;
                }
            }

            /* --------- sprite changer ----------- */

            // every time the sprite counter is over 5, the sprite num will changes by one, the sprite num is the index
            // for the sprites array
            ++spriteCounter;
            if (spriteCounter > 5) {
                ++spriteNum;
                spriteCounter = 0;
            }

            if (spriteNum > 2) {
                spriteNum = 0;
            }
        } else {
            spriteNum = 1;
        }

    }

    public void pegarItem(int index) {
        List<Item> itens = gp.getItens();

        if (index != 999) {
            itens.remove(index);
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

        int x = screenX;
        int y = screenY;

        if (screenX > envX) {
            x = envX;
        }
        if (screenY > envY) {
            y = envY;
        }

        int rightOffset = gp.screenWidth - screenX;

        if (rightOffset > gp.envWidth - envX) {
            x = gp.screenWidth - (gp.envWidth - envX);
        }

        int bottomOffset = gp.screenHeight - screenY;

        if (bottomOffset > gp.envHeight - envY) {
            y = gp.screenHeight - (gp.envHeight - envY);
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
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
