package onlineShop.models.products.components;

public class PowerSupply extends BaseComponent{
    private static final double MULTIPLIER = 1.05;

    public PowerSupply(int Id, String manufacturer, String model,
                       double price, double overallPerformance, int generation) {

        super(Id, manufacturer, model, price, MULTIPLIER * overallPerformance, generation);
    }
}
