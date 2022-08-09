package christmasRaces.entities.cars;

import christmasRaces.common.DataValidator;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{
    private static final int CUBIC_CENTIMETERS = 5000;
    private static final int MINIMUM_HORSEPOWER = 400;
    private static final int MAXIMUM_HORSEPOWER = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsePower(int horsePower) {
        String exceptionMessage = String.format(INVALID_HORSE_POWER, horsePower);
        DataValidator.validateHorsepower(horsePower, MINIMUM_HORSEPOWER, MAXIMUM_HORSEPOWER, exceptionMessage);
        super.setHorsePower(horsePower);
    }
}
