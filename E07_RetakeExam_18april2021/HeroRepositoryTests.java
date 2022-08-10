package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeroRepositoryTests {

    private HeroRepository heroRepository;
    private Hero hulk;

    private static final String HERO_NAME_NULL = null;
    private static final String HERO_NAME_HULK = "Hulk";
    private static final String HERO_NAME_BATMAN = "Batman";

    private static final int HERO_LEVEL_HUNDRED = 100;
    private static final int HERO_LEVEL_FIFTY = 50;

    private static final int HERO_COUNT_TWO = 2;

    private static final Hero BATMAN = new Hero(HERO_NAME_BATMAN, HERO_LEVEL_FIFTY);
    private static final Hero NULL_HERO = null;
    private static final Hero HERO_WITH_EXISTING_NAME = new Hero(HERO_NAME_HULK, HERO_LEVEL_FIFTY);

    private static final String SUCCESSFULLY_CREATED_HERO = "Successfully added hero %s with level %d";

    @Before
    public void setUp() {
        heroRepository = new HeroRepository();
        hulk = new Hero(HERO_NAME_HULK, HERO_LEVEL_HUNDRED);
        heroRepository.create(hulk);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_FoTryingToCreateHeroWithValueNull() {
        heroRepository.create(NULL_HERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForAlreadyExistingHeroWithThatName() {
        heroRepository.create(HERO_WITH_EXISTING_NAME);
    }

    @Test
    public void test_ShouldCreate_HeroCorrectly_AndReturnTheInformationAboutCreation() {
        String expectedMessage = String.format(SUCCESSFULLY_CREATED_HERO, HERO_NAME_BATMAN, HERO_LEVEL_FIFTY);
        String actualMessage = heroRepository.create(BATMAN);

        assertEquals(expectedMessage, actualMessage);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_ForNameWithValueNull() {
        heroRepository.remove(HERO_NAME_NULL);
    }

    @Test
    public void test_ShouldRemove_HeroFromTheRepositoryByTheGivenName() {
        boolean isRemoved = heroRepository.remove(HERO_NAME_HULK);
        assertTrue(isRemoved);
    }

    @Test
    public void test_ShouldGet_TheHeroWithTheHighestLevel() {
        heroRepository.create(BATMAN);

        int actualHeroCount = heroRepository.getCount();
        assertEquals(HERO_COUNT_TWO, actualHeroCount);

        Hero heroWithHighestLevel = heroRepository.getHeroWithHighestLevel();
        assertEquals(hulk, heroWithHighestLevel);
    }

    @Test
    public void test_ShouldGet_HeroByTheGivenNameCorrectly() {
        Hero heroByName = heroRepository.getHero(HERO_NAME_HULK);
        assertEquals(hulk, heroByName);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_ShouldThrow_Exception_ForTryingToModifyUnmodifiableCollection() {
        heroRepository.getHeroes().clear();
    }

}
