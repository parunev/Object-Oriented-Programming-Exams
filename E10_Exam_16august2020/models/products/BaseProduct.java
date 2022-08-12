package onlineShop.models.products;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseProduct implements Product{
    private int Id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    public BaseProduct(int Id, String manufacturer, String model, double price, double overallPerformance){
        this.setId(Id);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setPrice(price);
        this.setOverallPerformance(overallPerformance);
    }

    private void setId(int Id) {
        if (Id <= 0){
            throw new IllegalArgumentException(INVALID_PRODUCT_ID);
        }
        this.Id = Id;
    }

    private void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().isEmpty()){
            throw new IllegalArgumentException(INVALID_MANUFACTURER);
        }
        this.manufacturer = manufacturer;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty()){
            throw new IllegalArgumentException(INVALID_MODEL);
        }
        this.model = model;
    }

    private void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    private void setOverallPerformance(double overallPerformance) {
        if (overallPerformance <= 0){
            throw new IllegalArgumentException(INVALID_OVERALL_PERFORMANCE);
        }
        this.overallPerformance = overallPerformance;
    }

    @Override
    public int getId() {
        return this.Id;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getOverallPerformance() {
        return this.overallPerformance;
    }

    @Override
    public String toString(){
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)"
                ,getOverallPerformance(), getPrice(), this.getClass().getSimpleName(),
                getManufacturer(),getModel(),getId());
    }
}
