package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area{
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.animals.size() >= capacity){
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        this.animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();

        String names = this.animals
                .isEmpty()
                ? "none"
                : this.animals
                .stream()
                .map(Animal::getName)
                .collect(Collectors.joining(" "));

        info.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());

        if (this.animals.size() == 0) {
            info.append("Animal: none");
        } else {
            info.append(String.format("Animal: %s", names));
        }

        info.append(System.lineSeparator());
        info.append(String.format("Food: %d", this.foods.size())).append(System.lineSeparator());
        info.append(String.format("Calories: %d", this.sumCalories())).append(System.lineSeparator());

        return info.toString().trim();
    }
}
