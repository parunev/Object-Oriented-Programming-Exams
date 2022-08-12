package onlineShop.models.products.computers;

public class DesktopComputer extends BaseComputer{
    private static final double OVERALL_PERFORMANCE = 15;

    public DesktopComputer(int Id, String manufacturer, String model, double price) {
        super(Id, manufacturer, model, price, OVERALL_PERFORMANCE);
    }
}
