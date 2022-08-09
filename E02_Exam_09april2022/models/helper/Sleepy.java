package fairyShop.models.helper;

public class Sleepy extends BaseHelper{
    private static final int INITIAL_ENERGY = 50;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work(){
        this.setEnergy(Math.max(0, INITIAL_ENERGY - 15));
    }
}
