package catHouse.entities.cat;

import catHouse.common.DataValidator;

import static catHouse.common.ExceptionMessages.*;

public abstract class BaseCat implements Cat{

    private String name;
    private String breed;
    private int kilograms;
    private double price;

    public BaseCat(String name, String breed, double price){
        this.setName(name);
        this.setBreed(breed);
        this.setPrice(price);
    }

    private void setPrice(double price) {
        DataValidator.validateCatPrice(price, CAT_PRICE_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        this.price = price;
    }

    private void setBreed(String breed) {
        DataValidator.validateString(breed, CAT_BREED_CANNOT_BE_NULL_OR_EMPTY);
        this.breed = breed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        DataValidator.validateString(name, CAT_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    @Override
    public int getKilograms() {
        return this.kilograms;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void eating() {

    }

    protected void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }
}
