package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private Repository decorations;
    private Map<String, Aquarium> aquariums;

    public ControllerImpl(){
        this.decorations = new DecorationRepository();
        this.aquariums = new LinkedHashMap<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;

        switch (aquariumType){
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        this.aquariums.put(aquariumName, aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE,aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;

        switch (type){
            case"Ornament":
                decoration = new Ornament();
                break;
            case"Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }

        this.decorations.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorations.findByType(decorationType);

        if (decoration == null){
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        Aquarium aquarium = this.aquariums.get(aquariumName);

        aquarium.getDecorations().add(decoration);
        this.decorations.remove(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;

        switch (fishType){
            case"FreshwaterFish":
                fish = new FreshwaterFish(fishName,fishSpecies,price);
                break;
            case"SaltwaterFish":
                fish = new SaltwaterFish(fishName,fishSpecies,price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
        Aquarium aquarium = this.aquariums.get(aquariumName);
        String typeAquarium = aquarium.getClass().getSimpleName();
        boolean areSuitable = checkIfSuitable(fishType, typeAquarium);

        if (!areSuitable){
            return WATER_NOT_SUITABLE;
        }

        aquarium.addFish(fish);

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    private boolean checkIfSuitable(String fishType, String typeAquarium) {
        return (typeAquarium.equals("FreshwaterAquarium"))
                && (fishType.equals("FreshwaterFish"))
                || (typeAquarium.equals("SaltwaterAquarium"))
                && (fishType.equals("SaltwaterFish"));
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = this.aquariums.get(aquariumName);
        aquarium.feed();

        int fishInAquarium = aquarium.getFish().size();
        return String.format(FISH_FED, fishInAquarium);
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = this.aquariums.get(aquariumName);

        double decorationPrice = CalculateDecorationPrice(aquarium);
        double fishPrice = CalculateFishPrice(aquarium);
        double totalPrice = decorationPrice + fishPrice;

        return String.format(VALUE_AQUARIUM, aquarium.getName(), totalPrice);
    }

    private double CalculateFishPrice(Aquarium aquarium) {
        return aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
    }

    private double CalculateDecorationPrice(Aquarium aquarium) {
        return aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        this.aquariums.values()
                .forEach(aquarium -> sb.append(aquarium.getInfo())
                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
