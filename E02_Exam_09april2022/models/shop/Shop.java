package fairyShop.models.shop;

import fairyShop.models.helper.Helper;
import fairyShop.models.presents.Present;

public interface Shop {
    void craft(Present present, Helper helper);
}
