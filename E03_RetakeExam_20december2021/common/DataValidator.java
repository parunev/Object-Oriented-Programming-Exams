package christmasRaces.common;

import christmasRaces.entities.cars.Car;

public class DataValidator {

    public static void validateString(String dataToValidate, String exceptionMessage, int symbols) {
        if (dataToValidate == null || dataToValidate.trim().length() < symbols) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void validateHorsepower(int dataToValidate, int minimumHorsepower, int maximumHorsepower, String exceptionMessage) {
        if (dataToValidate < minimumHorsepower || dataToValidate > maximumHorsepower) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void validateCar(Car car, String exceptionMessage) {
        if (car == null) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void validateLaps(int laps, int minimumLaps, String exceptionMessage) {
        if (laps < minimumLaps) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
