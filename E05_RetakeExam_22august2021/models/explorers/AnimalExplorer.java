package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{
    private static final int INITIAL_ENERGY_UNITS = 100;

    public AnimalExplorer(String name) {
        super(name, INITIAL_ENERGY_UNITS);
    }
}
