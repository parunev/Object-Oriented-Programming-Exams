package onlineShop.models.products.components;

public class VideoCard extends BaseComponent{
    private static final double MULTIPLIER = 1.20;

    public VideoCard(int Id, String manufacturer, String model,
                     double price, double overallPerformance, int generation) {

        super(Id, manufacturer, model, price, MULTIPLIER *overallPerformance, generation);
    }
}
