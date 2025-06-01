package game.logic;

import game.graphics.GamePanel;

import java.awt.*;

public class Clock {

    private int dayState;
    private int time;
    private int quarter;
    private int daysPassed;

    private final int day = 0;
    private final int dusk = 1;
    private final int night = 2;
    private final int dawn = 3;

    public Clock() {
        this.dayState = day;
        this.quarter = 600;  // each quarter of the day lasts 2 minutes
        this.time = quarter;
    }

    public void update() {

        if (dayState == dawn) {
            time++;

            if (time > quarter) {
                dayState = day;
            }
        }
        if (dayState == day) {
            time++;

            if (time > 2*quarter) {
                dayState = dusk;
            }
        }
        if (dayState == dusk) {
            time++;

            if (time > 3*quarter) {
                dayState = night;
            }
        }
        if (dayState == night) {
            time++;

            if (time > 4*quarter) {
                dayState = dawn;
                time = 0;
                daysPassed++;
            }
        }
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        Color c = new Color(0, 0, 0, 0);

        switch (dayState) {
            case 0: c = new Color(0, 0, 0, 0); break;
            case 1: c = new Color(166, 103, 171, 100); break;
            case 2: c = new Color(0, 0, 130, 130); break;
            case 3: c = new Color(231, 156, 99, 100); break;
        }

        g2.setColor(c);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    }

    public int getTime() {
        return time;
    }

    public int getDaysPassed() {
        return daysPassed;
    }
}
