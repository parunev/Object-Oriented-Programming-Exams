package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component {
    private int generation;

    public BaseComponent(int Id, String manufacturer, String model,
                         double price, double overallPerformance, int generation) {

        super(Id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - " +
                        "%s: %s %s (Id: %d) Generation: %d",
                getOverallPerformance(), getPrice(), this.getClass().getSimpleName(),
                getManufacturer(), getModel(), getId(), this.generation);
    }
}
