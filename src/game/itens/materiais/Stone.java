package game.itens.materiais;

public class Stone extends Material{
    public Stone() {
        super("stone", 1);

        updateDescription();
    }

    @Override
    public void updateDescription() {
        setDescription("Regular wood.\nCan be used for crafting.");
    }
}
