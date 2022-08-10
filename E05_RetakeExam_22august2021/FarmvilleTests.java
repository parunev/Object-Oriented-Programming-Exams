package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Farm farm;
    private Animal dog;

    private static final String FARM_NAME = "AREA51";
    private static final String NULL_NAME = null;
    private static final int FARM_CAPACITY = 2;
    private static final int FARM_INVALID_CAPACITY = -2;
    private static final int FARM_ANIMALS_COUNT = 1;

    private static final String DOG_TYPE = "Dog";
    private static final String COW_TYPE = "Cow";
    private static final String SHEEP_TYPE = "Sheep";
    private static final int ANIMAL_ENERGY = 100;

    private static final Animal cow = new Animal(COW_TYPE, ANIMAL_ENERGY);
    private static final Animal sheep = new Animal(SHEEP_TYPE, ANIMAL_ENERGY);

    @Before
    public void setUp(){
        farm = new Farm(FARM_NAME, FARM_CAPACITY);
        dog = new Animal(DOG_TYPE, ANIMAL_ENERGY);
        farm.add(dog);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForNoSpaceLeft(){
        farm.add(cow);
        farm.add(sheep);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForExistingAnimal(){
        farm.add(dog);
    }

    @Test
    public void test_ShouldRemove_Animal_ByGivenNameFromTheFarm(){
        boolean isRemoved = farm.remove(DOG_TYPE);
        Assert.assertTrue(isRemoved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForInvalidCapacity(){
        new Farm(FARM_NAME,FARM_INVALID_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrow_Exception_ForInvalidName(){
        new Farm(NULL_NAME, FARM_CAPACITY);
    }

    @Test
    public void test_ShouldReturn_ExactAmountOfAnimals(){
        int exactAmountAnimals = farm.getCount();
        Assert.assertEquals(FARM_ANIMALS_COUNT, exactAmountAnimals);
    }

    @Test
    public void test_ShouldReturn_ExactNameOfTheFarm(){
        String exactFarmName = farm.getName();
        Assert.assertEquals(FARM_NAME, exactFarmName);
    }

}
