package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal{
    private static final double KILOGRAMS = 2.50;
    private static final double INCREASE_IN_KILOGRAMS = 7.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind,  price);
    }

    @Override
    public void eat(){
        this.setKg(KILOGRAMS + INCREASE_IN_KILOGRAMS);
    }
}
