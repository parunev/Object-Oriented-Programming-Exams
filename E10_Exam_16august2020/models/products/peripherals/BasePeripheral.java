package onlineShop.models.products.peripherals;

import onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {
    private String connectionType;

    public BasePeripheral(int Id, String manufacturer, String model,
                          double price, double overallPerformance, String connectionType) {

        super(Id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - " +
                        "%s: %s %s (Id: %d) Connection Type: %s",
                getOverallPerformance(), getPrice(), this.getClass().getSimpleName(),
                getManufacturer(), getModel(), getId(), this.connectionType);
    }
}
