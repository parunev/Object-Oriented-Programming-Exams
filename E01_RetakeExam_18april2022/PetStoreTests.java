package petStore;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PetStoreTests {
    private PetStore petStore;
    private Animal animal;

    private static final Animal lion = new Animal("Mammal",110,75);

    private static final String ANIMAL_SPECIE = "Mammal";
    private static final int MAX_KILOGRAMS = 120;
    private static final double ANIMAL_PRICE = 200;

    private static final int ANIMALS_COUNT_ONE = 1;
    private static final int FIFTY_KILOGRAMS = 50;

    @Before
    public void setUp() {
        petStore = new PetStore();
        animal = new Animal(ANIMAL_SPECIE, MAX_KILOGRAMS, ANIMAL_PRICE);
        petStore.addAnimal(animal);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldThrowExceptionForTryingToModifyUnmodifiableCollection() {
        petStore.getAnimals().clear();
    }

    @Test
    public void testShouldGetAnimalCountCorrectly() {
        int actualCount = petStore.getCount();
        assertEquals(ANIMALS_COUNT_ONE, actualCount);
    }

    @Test
    public void testShouldFindAllAnimalsWithMaxKilogramsByCertainNumber() {
        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(FIFTY_KILOGRAMS);
        assertEquals(animal, allAnimalsWithMaxKilograms.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionForNullAnimal() {
        petStore.addAnimal(null);
    }

    @Test
    public void testShouldGetTheMostExpensiveAnimal() {
        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal, theMostExpensiveAnimal);
    }

    @Test
    public void testShouldFindAllAnimalsByGivenSpecie() {
        List<Animal> allAnimalBySpecie = petStore.findAllAnimalBySpecie(ANIMAL_SPECIE);
        assertEquals(animal, allAnimalBySpecie.get(0));
    }
}

