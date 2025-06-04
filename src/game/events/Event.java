package game.events;

import game.graphics.GamePanel;

public abstract class Event {
    private String name;
    private String message;
    private double probability;

    public Event(String name, String message, double probability) {
        this.name = name;
        this.message = message;
        this.probability = probability;
    }

    public abstract void execute(GamePanel gp);

    public String getMessage() {
        return message;
    }

    public double getProbability() {
        return probability;
    }
}

