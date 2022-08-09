package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car car;

    private static final String CAR_BRAND = "Audi";
    private static final int CAR_MAXSPEED = 245;
    private static final double CAR_PRICE = 25000;
    private static final int CARS_INSIDE_GARAGE = 1;

    @Before
    public void setUp(){
        garage = new Garage();
        car = new Car(CAR_BRAND,CAR_MAXSPEED,CAR_PRICE);
        garage.addCar(car);
    }

    @Test
    public void test_ShouldGetCarsCount(){
        int garageCarsCount = garage.getCount();
        Assert.assertEquals(garageCarsCount, CARS_INSIDE_GARAGE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_ShouldThrowExceptionForTryingToModifyUnmodifiableList(){
        garage.getCars().clear();
    }

    @Test
    public void test_ShouldFindAllCarsWithMaxSpeedAbove(){
        List<Car> allCarsWithMaxSpeed = garage.findAllCarsWithMaxSpeedAbove(200);
        Assert.assertEquals(car,allCarsWithMaxSpeed.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrowExceptionIfCarIsNull(){
        garage.addCar(null);
    }

    @Test
    public void test_ShouldFindTheMostExpensiveCar(){
        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(car,mostExpensiveCar);

        Assert.assertEquals(1, garage.getCount());
    }

    @Test
    public void test_ShouldFindAllCarsByBrand(){
        List<Car> allCarBrands = garage.findAllCarsByBrand("Audi");
        Assert.assertEquals(car, allCarBrands.get(0));
    }
}