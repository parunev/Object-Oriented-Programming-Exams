package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food{
    private static final double BISCUITS_PORTION = 205;

    public VeganBiscuits(String name, double price) {
        super(name, BISCUITS_PORTION, price);
    }
}
