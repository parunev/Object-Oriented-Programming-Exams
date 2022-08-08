package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    private static final double KILOGRAMS = 5.50;
    private static final double INCREASE_IN_KILOGRAMS = 5.70;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, price);
    }

    @Override
    public void eat() {
        this.setKg(KILOGRAMS + INCREASE_IN_KILOGRAMS);
    }
}
