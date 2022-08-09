package fairyShop.models.presents;

import static fairyShop.common.ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO;
import static fairyShop.common.ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY;

public class PresentImpl implements Present{
    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired){
        this.setName(name);
        this.setEnergyRequired(energyRequired);
    }

    private void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0){
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {
        return this.energyRequired == 0;
    }

    @Override
    public void getCrafted() {
        this.energyRequired = Math.max(0, this.energyRequired - 10);
    }
}
