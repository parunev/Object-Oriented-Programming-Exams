package onlineShop.models.products.components;

public class Motherboard extends BaseComponent{
    private static final double MULTIPLIER = 1.25;

    public Motherboard(int Id, String manufacturer, String model,
                       double price, double overallPerformance, int generation) {

        super(Id, manufacturer, model, price, MULTIPLIER * overallPerformance, generation);
    }
}
