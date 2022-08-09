package christmasRaces.entities.cars;

import christmasRaces.common.DataValidator;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{
    private static final int MINIMUM_HORSEPOWER = 250;
    private static final int MAXIMUM_HORSEPOWER = 450;

    private static final double CUBIC_CENTIMETERS = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsePower(int horsePower){
        String exceptionMessage = String.format(INVALID_HORSE_POWER, horsePower);
        DataValidator.validateHorsepower(horsePower, MINIMUM_HORSEPOWER, MAXIMUM_HORSEPOWER, exceptionMessage);
        super.setHorsePower(horsePower);
    }
}
