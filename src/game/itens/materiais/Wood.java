package game.itens.materiais;

public class Wood extends Material{
    public Wood() {
        super("Madeira", 0.5);

        setImage("/itens/wood.png");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        setDescription("Regular wood.\nCan be used for crafting.");
    }
}
