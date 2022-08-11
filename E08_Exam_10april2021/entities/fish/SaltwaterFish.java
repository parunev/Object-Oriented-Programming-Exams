package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{
    private static int SIZE = 5;
    private static final int INCREASED_SIZE_PER_MEAL = 2;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    public void eat() {
        this.setSize(SIZE + INCREASED_SIZE_PER_MEAL);
    }
}
