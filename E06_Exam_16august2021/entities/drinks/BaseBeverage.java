package restaurant.entities.drinks;

import restaurant.entities.drinks.interfaces.Beverages;

import static restaurant.common.ExceptionMessages.*;

public class BaseBeverage implements Beverages {
    private String name;
    private int counter;
    private double price;
    private String brand;

    public BaseBeverage(String name, int counter, double price, String brand) {
        this.setName(name);
        this.setCounter(counter);
        this.setPrice(price);
        this.setBrand(brand);
    }

    private void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()){
            throw new IllegalArgumentException(INVALID_BRAND);
        }
        this.brand = brand;
    }

    private void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    private void setCounter(int counter) {
        if (counter<=0){
            throw new IllegalArgumentException(INVALID_COUNTER);
        }
        this.counter = counter;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }
}
