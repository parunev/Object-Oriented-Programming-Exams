package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private static final int INITIAL_UNITS_OF_OXYGEN = 70;
    private static final int DECREASE_UNITS_OF_OXYGEN = 5;
    private static final int ZERO_UNITS_OF_OXYGEN = 0;

    public Biologist(String name) {
        super(name, INITIAL_UNITS_OF_OXYGEN);
    }

    @Override
    public void breath(){
        if (getOxygen() <= DECREASE_UNITS_OF_OXYGEN){
            super.setOxygen(ZERO_UNITS_OF_OXYGEN);
        } else {
            super.setOxygen(this.getOxygen() - DECREASE_UNITS_OF_OXYGEN);
        }
    }
}
