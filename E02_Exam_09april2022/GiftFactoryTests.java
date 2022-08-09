package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private Gift gift;

    private static final String GIFT_TYPE = "Car";
    private static final double GIFT_MAGIC = 100;

    private static final String GIFT_BALL = "Ball";
    private static final double GIFT_MAGIC_TWO = 200;

    private static final int ZERO_COUNT_GIFTS = 0;
    private static final int GIFTS_COUNT = 1;
    private static final int GIFTS_COUNT_TWO = 2;

    private static final String SUCCESSFULLY_ADDED_GIFT = "Successfully added gift Ball with magic 200.00.";

    @Before
    public void setUp() {
        giftFactory = new GiftFactory();
        gift = new Gift(GIFT_TYPE, GIFT_MAGIC);
        giftFactory.createGift(gift);
    }

    @Test
    public void test_ShouldGetGiftsCount(){
        int giftFactoryCount = giftFactory.getCount();
        Assert.assertEquals(giftFactoryCount, GIFTS_COUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ShouldThrowExceptionIfGiftExists(){
        giftFactory.createGift(new Gift(GIFT_TYPE,GIFT_MAGIC));
    }

    @Test
    public void test_ShouldReturnCorrectMessageForCreatingAGift(){
        int startCount = giftFactory.getCount();
        assertEquals(1, startCount);

        String giftFactoryGift = giftFactory.createGift(new Gift(GIFT_BALL, GIFT_MAGIC_TWO));
        assertEquals(SUCCESSFULLY_ADDED_GIFT,giftFactoryGift);

        int count = giftFactory.getCount();
        assertEquals(GIFTS_COUNT_TWO,count);
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrowExceptionForTryingToRemoveGift(){
        giftFactory.removeGift(null);
    }

    @Test
    public void test_ShouldRemoveGiftByGivenNameFromFactoryCorrectly(){
        boolean isRemoved = giftFactory.removeGift(GIFT_TYPE);
        assertTrue(isRemoved);
        assertEquals(ZERO_COUNT_GIFTS, giftFactory.getCount());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_ShouldThrowExceptionForTryingToModifyUnmodifiableCollection() {
        giftFactory.getPresents().clear();
    }

    @Test
    public void test_ShouldReturnCertainGiftByTheGivenName() {
        Gift present = giftFactory.getPresent(GIFT_TYPE);
        assertEquals(gift, present);
    }

    @Test
    public void test_ShouldGetPresentWithLeastMagic() {
        giftFactory.createGift(new Gift(GIFT_BALL, GIFT_MAGIC_TWO));
        Gift presentWithLeastMagic = giftFactory.getPresentWithLeastMagic();
        assertEquals(gift, presentWithLeastMagic);
    }

    @Test
    public void test_ShouldGetPresentType() {
        String type = gift.getType();
        assertEquals(GIFT_TYPE, type);
    }

    @Test
    public void test_ShouldGetPresents() {
        List<Gift> presents = new ArrayList<>(giftFactory.getPresents());
        assertEquals(gift, presents.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void test_ShouldThrowExceptionForEmptyNameToRemove() {
        giftFactory.removeGift(" ");
    }

    @Test
    public void test_ShouldReturnNullForPresentWithNoSuchName() {
        Gift noneExisting = giftFactory.getPresent("noneExisting");
        assertNull(noneExisting);
    }

    @Test
    public void test_ShouldReturnNullForNoSuchPresentWithLeastMagic() {
        giftFactory.removeGift(GIFT_TYPE);
        Gift presentWithLeastMagic = giftFactory.getPresentWithLeastMagic();
        assertNull(presentWithLeastMagic);
    }
}
