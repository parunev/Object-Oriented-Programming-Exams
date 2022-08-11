package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{
    private static int SIZE = 3;
    private static final int INCREASED_SIZE_PER_MEAL = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        this.setSize(SIZE + INCREASED_SIZE_PER_MEAL);
    }
}
