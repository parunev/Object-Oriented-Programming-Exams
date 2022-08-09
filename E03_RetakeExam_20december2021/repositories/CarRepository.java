package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {

    private Map<String, Car> cars;

    public CarRepository(){
        this.cars = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.cars.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.cars.values());
    }

    @Override
    public void add(Car model) {
        this.cars.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        return this.cars.remove(model.getModel()) != null ;
    }
}
