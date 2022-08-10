package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.BeverageRepositoryImpl;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.TableRepositoryImpl;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;


    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {

        this.healthFoodRepository = new HealthFoodRepositoryImpl();
        this.beverageRepository = new BeverageRepositoryImpl();
        this.tableRepository = new TableRepositoryImpl();
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food = type.equals("Salad")
                ? new Salad(name, price)
                : new VeganBiscuits(name, price);

        HealthyFood previouslyAdded = this.healthFoodRepository.foodByName(name);

        if (previouslyAdded != null){
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }

        this.healthFoodRepository.add(food);
        return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage = type.equals("Fresh")
                ? new Smoothie(name, counter, brand)
                : new Fresh(name, counter, brand);

        Beverages previouslyAdded = this.beverageRepository.beverageByName(name, brand);

        if (previouslyAdded != null){
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }

        this.beverageRepository.add(beverage);
        return String.format(BEVERAGE_ADDED, type ,brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = type.equals("Indoors")
                ? new Indoors(tableNumber, capacity)
                : new InGarden(tableNumber, capacity);

        Table previouslyAdded = this.tableRepository.byNumber(tableNumber);

        if (previouslyAdded != null){
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        this.tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table suitableTable = this.tableRepository
                .getAllEntities()
                .stream()
                .filter(table -> !table.isReservedTable() &&
                        table.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (suitableTable == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        suitableTable.reserve(numberOfPeople);
        return String.format(TABLE_RESERVED, suitableTable.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood healthyFood = this.healthFoodRepository.foodByName(healthyFoodName);

        if (healthyFood == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(healthyFood);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverages = this.beverageRepository.beverageByName(name, brand);

        if (beverages == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverages);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);

        double tableBill = table.bill();
        table.clear();
        totalMoney += tableBill;

        return String.format(BILL, tableNumber, tableBill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, totalMoney);
    }
}
