package fairyShop.models.helper;

import fairyShop.models.instruments.Instrument;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_ENERGY_LESS_THAN_ZERO;
import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper {

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    private static final int DECREASE_ENERGY = 10;
    private static final int ZERO_ENERGY = 0;

    public BaseHelper(String name, int energy){
        this.setName(name);
        this.setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(int energy) {
        if (energy < ZERO_ENERGY) {
            throw new IllegalArgumentException(HELPER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public void work() {
        this.energy = Math.max(ZERO_ENERGY, this.energy - DECREASE_ENERGY);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.energy > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }
}
