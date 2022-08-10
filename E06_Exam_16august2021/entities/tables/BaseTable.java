package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson){
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        this.pricePerPerson = pricePerPerson;

        this.setSize(size);

    }

    private void setSize(int size) {
        if (size <= 0){
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int getTableNumber() {return this.number;}

    @Override
    public int getSize() {return this.size;}

    @Override
    public int numberOfPeople() {return this.numberOfPeople;}

    @Override
    public double pricePerPerson() {return this.pricePerPerson;}

    @Override
    public boolean isReservedTable() {return this.isReservedTable;}

    @Override
    public double allPeople() {return this.allPeople;}

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
        this.isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double healthyFoodPrice = this.healthyFood
                .stream()
                .mapToDouble(HealthyFood::getPrice)
                .sum();

        double beveragesPrice = this.beverages
                .stream()
                .mapToDouble(Beverages::getPrice)
                .sum();

        return (healthyFoodPrice + beveragesPrice) + this.numberOfPeople * this.pricePerPerson;
    }

    @Override
    public void clear() {
        this.healthyFood.clear();
        this.beverages.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
    }

    @Override
    public String tableInformation() {
        return String.format("Table - %d" +
                "Size - %d" +
                "Type - %s"+
                "All price - %.2f"
                ,this.number,this.size,this.getClass().getSimpleName(), this.pricePerPerson);
    }
}
