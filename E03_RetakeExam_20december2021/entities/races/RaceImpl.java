package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static christmasRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race{
    private String name;
    private int laps;
    private Map<String, Driver> drivers;

    public RaceImpl(String name, int laps){
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new LinkedHashMap<>();
    }

    private void setLaps(int laps) {
        if (laps < 1){
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS,1));
        }
        this.laps = laps;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5){
            throw new IllegalArgumentException(String.format(INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers.values();
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        }

        if (!driver.getCanParticipate()) {
            String exceptionMessage = String.format(DRIVER_NOT_PARTICIPATE, driver.getName());
            throw new IllegalArgumentException(exceptionMessage);
        }

        boolean isExisting = this.drivers.get(driver.getName()) != null;

        if (isExisting) {
            String exceptionMessage = String.format(DRIVER_ALREADY_ADDED, driver.getName(), this.name);
            throw new IllegalArgumentException(exceptionMessage);
        }

        this.drivers.put(driver.getName(), driver);
    }
}
