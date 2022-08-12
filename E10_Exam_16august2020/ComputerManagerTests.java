package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputerManagerTests {
    private ComputerManager computerManager;
    private Computer computer;
    private static final Computer NULL_COMPUTER = null;

    private static final String COMPUTER_MANUFACTURER = "IGS";
    private static final String COMPUTER_MODEL = "KL-012";
    private static final double COMPUTER_PRICE = 100_000;

    private static final String NONE_EXISTING_MANUFACTURER = "MGB";
    private static final String NONE_EXISTING_MODEL = "AVOCADO-10";

    private static final int COMPUTER_INDEX = 0;
    private static final int COMPUTERS_COUNT = 1;

    @Before
    public void setUp() {
        computerManager = new ComputerManager();
        computer = new Computer(COMPUTER_MANUFACTURER, COMPUTER_MODEL, COMPUTER_PRICE);
        computerManager.addComputer(computer);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_ShouldThrow_Exception_ForTryingToModifyUnmodifiableCollection() {
        computerManager.getComputers().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_ForTryingToAddComputerWithValueNull() {
        computerManager.addComputer(NULL_COMPUTER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrow_Exception_WhenTryingToAddAlreadyExistingComputer() {
        computerManager.addComputer(computer);
    }

    @Test
    public void test_ShouldRemove_Computer_ByGivenManufacturerAndModel() {
        Computer removedComputer = computerManager.removeComputer(COMPUTER_MANUFACTURER, COMPUTER_MODEL);
        assertEquals(computer, removedComputer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldTrow_Exception_ForNoneExistingManufacturerAndModelInComputerManger() {
        computerManager.getComputer(NONE_EXISTING_MANUFACTURER, NONE_EXISTING_MODEL);
    }

    @Test
    public void test_ShouldGet_Computer_ByManufacturerAndModel() {
        Computer computerByManufacturerAndModel = computerManager.getComputer(COMPUTER_MANUFACTURER, COMPUTER_MODEL);
        assertEquals(computer, computerByManufacturerAndModel);
    }

    @Test
    public void test_ShouldGet_Computer_ByManufacturerCorrectly() {
        List<Computer> computersByManufacturer = computerManager.getComputersByManufacturer(COMPUTER_MANUFACTURER);
        assertEquals(computer, computersByManufacturer.get(COMPUTER_INDEX));
    }

    @Test
    public void test_ShouldGet_Computers_CountFromTheComputerManager() {
        int computerManagerCount = computerManager.getCount();
        assertEquals(COMPUTERS_COUNT, computerManagerCount);
    }
}