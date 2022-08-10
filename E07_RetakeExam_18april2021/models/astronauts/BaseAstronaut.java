package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO;

public class BaseAstronaut implements Astronaut{
    private String name;
    private double oxygen;
    private Bag bag;

    private static final int DECREASE_OXYGEN = 10;
    private static final int ZERO_OXYGEN = 0;

    public BaseAstronaut(String name, double oxygen){
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    void setOxygen(double oxygen) {
        if (oxygen < 0){
            throw new IllegalArgumentException(ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {
        this.oxygen = Math.max(ZERO_OXYGEN, this.oxygen - DECREASE_OXYGEN);
    }

    @Override
    public String toString() {
        String bagItems = this.getBag().getItems().size() == 0
                ? "none"
                : String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.bag.getItems());

        String info = String.format(REPORT_ASTRONAUT_NAME, this.name) + System.lineSeparator() +
                      String.format(REPORT_ASTRONAUT_OXYGEN, this.oxygen) + System.lineSeparator() +
                      String.format(REPORT_ASTRONAUT_BAG_ITEMS, bagItems);

        return info.trim();
    }

}
