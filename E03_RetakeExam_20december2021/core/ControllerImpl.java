package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> cars;
    private Repository<Driver> drivers;
    private Repository<Race> races;

    private static final int MINIMUM_DRIVERS_PER_RACE = 3;
    public ControllerImpl(Repository<Driver> drivers, Repository<Car> cars, Repository<Race> races ){
        this.cars = cars;
        this.drivers = drivers;
        this.races = races;
    }
    @Override
    public String createDriver(String driver) {
        Driver existingDriver = this.drivers.getByName(driver);

        if (existingDriver != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        Driver newDriver = new DriverImpl(driver);
        this.drivers.add(newDriver);

        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car existingCar = this.cars.getByName(model);

        if (existingCar != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car newCar = null;
        String carType = "";

        switch (type) {
            case "Muscle":
                newCar = new MuscleCar(model, horsePower);
                carType = "MuscleCar";
                break;
            case "Sports":
                newCar = new SportsCar(model, horsePower);
                carType = "SportsCar";
                break;
        }

        this.cars.add(newCar);
        return String.format(CAR_CREATED, carType, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.drivers.getByName(driverName);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = this.cars.getByName(carModel);

        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.races.getByName(raceName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = this.drivers.getByName(driverName);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.races.getByName(raceName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        int driversInTheRace = race.getDrivers().size();

        if (driversInTheRace < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        int laps = race.getLaps();
        Collection<Driver> drivers = race.getDrivers();

        List<Driver> fastestThree = drivers
                .stream()
                .sorted((d2, d1) -> Double.compare(d1.getCar().calculateRacePoints(laps),
                        d2.getCar().calculateRacePoints(laps)))
                .limit(3)
                .collect(Collectors.toList());

        this.races.remove(race);

        Driver first = fastestThree.get(0);
        Driver second = fastestThree.get(1);
        Driver third = fastestThree.get(2);

        StringBuilder message = new StringBuilder();

        String nameRace = race.getName();

        message.append(String.format(DRIVER_FIRST_POSITION, first.getName(), nameRace))
                .append(System.lineSeparator());
        message.append(String.format(DRIVER_SECOND_POSITION, second.getName(), nameRace))
                .append(System.lineSeparator());
        message.append(String.format(DRIVER_THIRD_POSITION, third.getName(), nameRace))
                .append(System.lineSeparator());

        return message.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race existingRace = this.races.getByName(name);

        if (existingRace != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        this.races.add(race);

        return String.format(RACE_CREATED, name);
    }
}
