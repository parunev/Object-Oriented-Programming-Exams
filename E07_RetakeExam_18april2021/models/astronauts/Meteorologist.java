package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{
    private static final int INITIAL_UNITS_OF_OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, INITIAL_UNITS_OF_OXYGEN);
    }
}
