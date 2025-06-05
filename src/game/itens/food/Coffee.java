package game.itens.food;

public class Coffee extends Food{
    private int energyRestored = 10;

    public Coffee() {
        super("Coffee", 0.25, 1, 10);

        setImage("/itens/coffee.png");
    }

    public int getEnergyRestored() {
        return energyRestored;
    }
}
