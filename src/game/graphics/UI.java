package game.graphics;

import java.awt.*;

public class UI {
    private GamePanel gp;
    private Font arial80B;
    private Graphics2D g2;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial80B = new Font("Arial", Font.BOLD, 80);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial80B);
        g2.setColor(Color.white);

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    private void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    private int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth/2 - length/2);
    }
}
