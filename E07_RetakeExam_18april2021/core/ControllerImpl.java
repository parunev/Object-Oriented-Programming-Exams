package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int exploredPlanets;

    public ControllerImpl(){
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;

        switch (type){
            case"Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case"Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case"Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }

        this.astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED,type,astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);

        for (String itm : items){
            planet.getItems().add(itm);
        }

        this.planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);

        if (astronaut == null ){
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST,astronautName));
        }

        this.astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = this.planetRepository.findByName(planetName);

        List<Astronaut> suitableAstronaut = this.astronautRepository.getModels().stream().filter(astronaut -> astronaut.getOxygen() > 60)
                .collect(Collectors.toList());

        if (suitableAstronaut.isEmpty()){
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();
        mission.explore(planet,suitableAstronaut);

        long retiredAstronaut = suitableAstronaut.stream().filter(astronaut -> astronaut.getOxygen() == 0).count();
        this.exploredPlanets++;

        return String.format(PLANET_EXPLORED, planetName, retiredAstronaut);
    }

    @Override
    public String report() {
        StringBuilder info = new StringBuilder();

        info.append(String.format(REPORT_PLANET_EXPLORED, this.exploredPlanets)).append(System.lineSeparator());
        info.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());

        this.astronautRepository
                .getModels()
                .forEach(astronaut -> info
                        .append(astronaut.toString())
                        .append(System.lineSeparator()));

        return info.toString().trim();
    }
}
