package viceCity.models.guns;

public class Rifle extends BaseGun{
    private static final int ZERO_BULLETS = 0;
    private static final int BULLETS_PER_FIRE = 5;
    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 100;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
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
