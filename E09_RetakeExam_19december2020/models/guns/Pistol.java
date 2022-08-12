package viceCity.models.guns;

public class Pistol extends BaseGun{
    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;
    private static final int ZERO_BULLETS = 0;
    private static final int BULLETS_PER_FIRE = 1;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire(){
        if (getBulletsPerBarrel() == ZERO_BULLETS && getTotalBullets() > ZERO_BULLETS) {
            reload();
        }

        if (getBulletsPerBarrel() > ZERO_BULLETS) {
            this.setBulletsPerBarrel(getBulletsPerBarrel() - BULLETS_PER_FIRE);
        }

        return BULLETS_PER_FIRE;
    }

    private void reload() {
        this.setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
        this.setBulletsPerBarrel(BULLETS_PER_BARREL);
    }
}
