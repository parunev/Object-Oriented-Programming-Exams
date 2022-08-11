package aquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AquariumTests {
    private Aquarium aquarium;
    private Fish fish;

    private static final String AQUARIUM_NAME = "BigAquarium";
    private static final int AQUARIUM_CAPACITY = 2;
    private static final int INVALID_CAPACITY = -10;
    private static final int ZERO_FISH_COUNT = 0;

    private static final String FISH_NAME = "Toshko";
    private static final String FISH2_NAME = "Goshko";
    private static final String FISH3_NAME = "Marto";
    private static final String NULL_AQUARIUM_NAME = null;

    private static final Fish GOSHKO = new Fish(FISH2_NAME);
    private static final Fish MARTO = new Fish(FISH3_NAME);
    private static final String REPORT_MESSAGE = "Fish available at %s: %s";

    @Before
    public void setUp() {
        aquarium = new Aquarium(AQUARIUM_NAME, AQUARIUM_CAPACITY);
        fish = new Fish(FISH_NAME);
        aquarium.add(fish);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_ForTryingToSetNullAquariumName() {
        new Aquarium(NULL_AQUARIUM_NAME, AQUARIUM_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForTryingToSetInvalidAquariumCapacity() {
        new Aquarium(AQUARIUM_NAME, INVALID_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForNoMoreSpaceInTheAquarium() {
        aquarium.add(GOSHKO);
        aquarium.add(MARTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldTrow_Exception_WhenTryingToRemoveNoneExistingFish() {
        aquarium.add(GOSHKO);
        aquarium.remove(FISH3_NAME);
    }

    @Test
    public void test_ShouldRemove_FishFromTheAquariumCorrectly() {
        aquarium.remove(FISH_NAME);
        assertEquals(ZERO_FISH_COUNT, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForTryingToRemoveFishWithNoSuchNameFromTheAquarium() {
        aquarium.sellFish(FISH3_NAME);
    }

    @Test
    public void test_ShouldSell_FishFromTheAquariumByTheGivenName() {
        Fish soldFish = aquarium.sellFish(FISH_NAME);
        assertEquals(fish, soldFish);
        assertFalse(soldFish.isAvailable());
    }

    @Test
    public void testShouldReturnTheReportForTheFishNamesInTheAquarium() {
        String expectedReport = String.format(REPORT_MESSAGE, aquarium.getName(), FISH_NAME);
        String aquariumReport = aquarium.report();
        assertEquals(expectedReport, aquariumReport);
    }

    @Test
    public void testShouldGetTheAquariumCapacityCorrectly() {
        int actualCapacity = aquarium.getCapacity();
        assertEquals(AQUARIUM_CAPACITY, actualCapacity);
    }

}

