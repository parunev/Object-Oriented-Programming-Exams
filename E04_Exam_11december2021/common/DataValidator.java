package catHouse.common;

public class DataValidator {

    private static final double INVALID_CAT_PRICE = 0;

    public static void validateString(String dataToValidate, String exceptionMessage) {
        if (dataToValidate == null || dataToValidate.trim().isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }
    }

    public static void validateCatPrice(double catPrice, String exceptionMessage) {
        if (catPrice <= INVALID_CAT_PRICE) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
