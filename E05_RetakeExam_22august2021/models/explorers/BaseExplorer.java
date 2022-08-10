package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY;

public abstract class BaseExplorer implements Explorer{
    private String name;
    private double energy;
    private Suitcase suitcase;

    private static final int DECREASE_EXPLORER_ENERGY = 15;
    private static final int ZERO_ENERGY = 0;

    public BaseExplorer(String name, double energy){
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase = new Carton();
    }

    public void setEnergy(double energy) {
        if (energy < 0){
            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canSearch() {
        return this.energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.energy = Math.max(ZERO_ENERGY, this.energy - DECREASE_EXPLORER_ENERGY);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        info.append(String.format(FINAL_EXPLORER_NAME, this.name)).append(System.lineSeparator());
        info.append(String.format(FINAL_EXPLORER_ENERGY, this.energy)).append(System.lineSeparator());
        info.append("Suitcase exhibits: ");

        int exhibitsCount = this.suitcase.getExhibits().size();

        String suitcaseExhibit = exhibitsCount == 0
                ? "None"
                : String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, this.getSuitcase().getExhibits());

        info.append(suitcaseExhibit);

        return info.toString().trim();
    }
}
