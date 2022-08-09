package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private static House house;
    private static Cat cat;

    private static final String HOUSE_NAME = "House of Cats";
    private static final String CAT_NAME = "Washington";
    private static final String NON_EXISTING_CAT = "Obama";
    private static final String STATISTICS_MSG =
            "The cat Washington is in the house House of Cats!";

    private static final int HOUSE_CAPACITY = 2;
    private static final int HOUSE_COUNT = 1;
    private static final int ZERO_COUNT = 0;
    private static final int INVALID_HOUSE_CAPACITY = -10;

    @Before
    public void setUp(){
        house = new House(HOUSE_NAME,HOUSE_CAPACITY);
        cat = new Cat(CAT_NAME);
        house.addCat(cat);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrowExceptionIfNameIsNull(){
        new House(null,HOUSE_CAPACITY);
    }

    @Test
    public void test_ShouldGetHouseCapacityCorrect(){
        Assert.assertEquals(HOUSE_CAPACITY, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrowExceptionForInvalidCapacity(){
        new House(HOUSE_NAME, INVALID_HOUSE_CAPACITY);
    }

    @Test
    public void test_ShouldGetCatCapacityCorrect(){
        Assert.assertEquals(HOUSE_COUNT,house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrowExceptionIfTheHouseIsFull(){
        house.addCat(cat);
        house.addCat(cat);
    }

    @Test
    public void test_ShouldRemoveCatCorrectlyFromHouse(){
        house.removeCat(cat.getName());
        Assert.assertEquals(ZERO_COUNT,house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrowExceptionForNoSuchCatName(){
        house.removeCat(NON_EXISTING_CAT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrowExceptionForNoSuchCatForSale(){
        house.catForSale(NON_EXISTING_CAT);
    }

    @Test
    public void test_ShouldReturnStatisticsMessage(){
        Assert.assertEquals(STATISTICS_MSG, house.statistics());
    }

}
