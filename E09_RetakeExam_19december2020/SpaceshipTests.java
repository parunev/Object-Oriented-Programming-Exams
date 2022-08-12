package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    private Astronaut astronaut;
    private Spaceship spaceship;

    private static final String ASTRONAUT_NAME = "Martin";
    private static final String ASTRONAUT_NULL_NAME = null;
    private static final double ASTRONAUT_OXYGEN_PERCENTAGE = 100;
    private static final double ASTRONAUT_OXYGEN_INVALID = -20;

    private static final String SECOND_ASTRONAUT_NAME = "Sunay";
    private static final int SECOND_ASTRONAUT_OXYGEN_PERCENTAGE = 99;

    private static final String THIRD_ASTRONAUT_NAME = "Petar";
    private static final int THIRD_ASTRONAUT_OXYGEN_PERCENTAGE = 98;

    private static final Astronaut SECOND_ASTRONAUT = new Astronaut(SECOND_ASTRONAUT_NAME, SECOND_ASTRONAUT_OXYGEN_PERCENTAGE);
    private static final Astronaut THIRD_ASTRONAUT = new Astronaut(THIRD_ASTRONAUT_NAME, THIRD_ASTRONAUT_OXYGEN_PERCENTAGE);

    private static final String SPACESHIP_NAME = "Apollo";
    private static final String SPACESHIP_NULL_NAME = null;
    private static final int SPACESHIP_CAPACITY = 2;
    private static final int SPACESHIP_INVALID_CAPACITY = -2;

    @Before
    public void setUp(){
        spaceship = new Spaceship(SPACESHIP_NAME, SPACESHIP_CAPACITY);
        astronaut = new Astronaut(ASTRONAUT_NAME, ASTRONAUT_OXYGEN_PERCENTAGE);
        spaceship.add(astronaut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForNoMoreCapacity(){
        spaceship.add(SECOND_ASTRONAUT);
        int actualCount = spaceship.getCount();
        assertEquals(SPACESHIP_CAPACITY, actualCount);

        spaceship.add(THIRD_ASTRONAUT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForNonExistingAstronaut(){
        spaceship.add(astronaut);
    }

    @Test
    public void test_ShouldRemove_Astronaut_ByName(){
        boolean isRemoved = spaceship.remove(ASTRONAUT_NAME);
        assertTrue(isRemoved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForInvalidCapacity(){
        new Spaceship(SPACESHIP_NAME, SPACESHIP_INVALID_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_ForInvalidName(){
        new Spaceship(SPACESHIP_NULL_NAME, SPACESHIP_CAPACITY);
    }

    @Test
    public void test_ShouldReturn_CorrectSpaceshipName(){
        String actualName = spaceship.getName();
        assertEquals(SPACESHIP_NAME, actualName);
    }




}
