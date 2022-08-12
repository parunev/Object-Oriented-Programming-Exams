package onlineShop.models.products.components;

public class CentralProcessingUnit extends BaseComponent{
    private static final double MULTIPLIER = 1.25;

    public CentralProcessingUnit(int Id, String manufacturer, String model,
                                 double price, double overallPerformance, int generation) {

        super(Id, manufacturer, model, price, overallPerformance * MULTIPLIER, generation);
    }
}
