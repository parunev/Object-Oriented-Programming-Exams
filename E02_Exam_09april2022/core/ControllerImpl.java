package fairyShop.core;

import fairyShop.models.helper.Happy;
import fairyShop.models.helper.Helper;
import fairyShop.models.helper.Sleepy;
import fairyShop.models.instruments.Instrument;
import fairyShop.models.instruments.InstrumentImpl;
import fairyShop.models.presents.Present;
import fairyShop.models.presents.PresentImpl;
import fairyShop.models.shop.Shop;
import fairyShop.models.shop.ShopImpl;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private Shop shop;
    private int craftedPresents;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;

        switch (type){
            case"Happy":
                helper = new Happy(helperName);
                break;
            case"Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        this.helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);

        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helper.getName());
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, present.getName());
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = this.presentRepository.findByName(presentName);

        Helper suitableHelper = this.helperRepository
                .getModels()
                .stream()
                .filter(helper -> helper.getEnergy() > 50)
                .findFirst()
                .orElse(null);

        if (suitableHelper == null) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        shop.craft(present, suitableHelper);

        long instrumentsCountAfter = suitableHelper
                .getInstruments()
                .stream()
                .filter(Instrument::isBroken)
                .count();

        String isDone;

        if (present.isDone()) {
            isDone = "done";
            craftedPresents++;
        } else {
            isDone = "not done";
        }

        return String.format(PRESENT_DONE, presentName, isDone)
                + String.format(COUNT_BROKEN_INSTRUMENTS, instrumentsCountAfter);
    }

    @Override
    public String report() {
        StringBuilder info = new StringBuilder();

        info.append(String.format("%d presents are done!", craftedPresents)).append(System.lineSeparator());
        info.append("Helpers info:").append(System.lineSeparator());

        this.helperRepository
                .getModels()
                .forEach(helper -> info
                        .append(helper.toString())
                        .append(System.lineSeparator()));

        return info.toString().trim();
    }
}
