package zoo.entities.animals;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseAnimal implements Animal{
    private String name;
    private String kind;
    private double kg;
    private double price;

    public BaseAnimal(String name, String kind, double price){
        this.setName(name);
        this.setKind(kind);
        this.setPrice(price);
    }

    private void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException(ANIMAL_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;

    }

    private void setKind(String kind) {
        if (kind == null || kind.trim().isEmpty()){
            throw new NullPointerException(ANIMAL_KIND_NULL_OR_EMPTY);
        }
        this.kind = kind;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ANIMAL_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setKg(double kg){
        this.kg = kg;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getKg() {
        return this.kg;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void eat() {
        this.kg += 5;
    }
}
