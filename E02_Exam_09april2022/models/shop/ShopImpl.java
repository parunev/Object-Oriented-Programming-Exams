package fairyShop.models.shop;

import fairyShop.models.helper.Helper;
import fairyShop.models.instruments.Instrument;
import fairyShop.models.presents.Present;

import java.util.ArrayList;
import java.util.List;

public class ShopImpl implements Shop{
    @Override
    public void craft(Present present, Helper helper) {
        List<Instrument> helperInstruments = new ArrayList<>(helper.getInstruments());

        while (helper.canWork()){

            Instrument instrument = helperInstruments
                    .stream()
                    .filter(instrument1 -> !instrument1.isBroken())
                    .findFirst()
                    .orElse(null);

            if (instrument == null){
                break;
            }

            while (!present.isDone()){
                helper.work();
                instrument.use();
                present.getCrafted();

                if (instrument.isBroken()){
                    instrument = helperInstruments
                            .stream()
                            .filter(instrument1 -> !instrument1.isBroken())
                            .findFirst()
                            .orElse(null);
                }

                if (!helper.canWork() || instrument == null) {
                    break;
                }
            }

            if (present.isDone()) {
                break;
            }
        }
    }
}
