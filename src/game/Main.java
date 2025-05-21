package game;

import game.graphics.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();  // creates the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close when 'x' pressed
        window.setResizable(false);  // fixed window size
        window.setTitle("The Last Frontier");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);  // window appears at the center
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}