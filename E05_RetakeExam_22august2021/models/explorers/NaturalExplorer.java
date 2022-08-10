package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final int INITIAL_ENERGY_UNITS = 60;
    private static final int DECREASE_IN_ENERGY = 7;
    private static final int ZERO_ENERGY = 0;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY_UNITS);
    }

    @Override
    public void search(){
        if (getEnergy() <= DECREASE_IN_ENERGY){
            super.setEnergy(ZERO_ENERGY);
        } else {
            super.setEnergy(this.getEnergy() - DECREASE_IN_ENERGY);
        }
    }
}
