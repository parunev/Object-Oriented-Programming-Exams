package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    private Gun gun;

    private static final String GUN_NAME = "Kalashnikov";
    private static final String GUN_NULL_NAME = null;
    private static final Gun NULL_GUN = null;

    private static final int GUN_BULLETS = 24;
    private static final int GUN_NEGATIVE_BULLETS = -10;
    private static final int GUN_DAMAGE1 = 50;
    private static final int GUN_DAMAGE2 = 80;
    private static final int GUN_DAMAGE3 = 100;

    private Player player;

    private static final String PLAYER_NAME = "Putin";
    private static final String PLAYER_NULL_NAME = null;

    private static final int PLAYER_HEALTH = 100;
    private static final int PLAYER_HALF_HEALTH = 50;
    private static final int PLAYER_NEGATIVE_HEALTH = -10;


    @Before
    public void setUp(){
        gun = new Gun(GUN_NAME, GUN_BULLETS);
        player = new Player(PLAYER_NAME, PLAYER_HEALTH);
        player.addGun(gun);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_ForInvalidName(){
        player = new Player(PLAYER_NULL_NAME, PLAYER_HEALTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForInvalidHealth(){
        player = new Player(PLAYER_NAME, PLAYER_NEGATIVE_HEALTH);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_ShouldThrow_Exception_ForTryingToModifyUnmodifiableCollection(){
        player.getGuns().clear();
    }

    @Test(expected = IllegalStateException.class)
    public void test_ShouldThrow_Exception_ForZeroHealth(){
        player.takeDamage(GUN_DAMAGE3);
        player.takeDamage(GUN_DAMAGE3);
    }

    @Test
    public void test_Player_ShouldTakeDamage_Correctly(){
        player.takeDamage(GUN_DAMAGE1);
        Assert.assertEquals(PLAYER_HALF_HEALTH, player.getHealth());

        player.takeDamage(GUN_DAMAGE3);
        int actualHealth = player.getHealth();
        Assert.assertEquals(0, actualHealth);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_ForInvalidGun(){
        player.addGun(NULL_GUN);
    }

    @Test
    public void test_ShouldRemove_Gun_Correctly(){
        boolean isRemoved = player.removeGun(gun);
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void test_ShouldGet_TheRightGun(){
        Gun gunByName = player.getGun(GUN_NAME);
        Assert.assertEquals(gun, gunByName);
    }

    @Test
    public void test_ShouldReturn_PlayerName_Correctly(){
        String playerName = player.getUsername();
        Assert.assertEquals(PLAYER_NAME, playerName);
    }
}
