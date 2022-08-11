package aquarium.repositories;

import aquarium.entities.decorations.Decoration;

public interface Repository<D> {
    void add(Decoration decoration);

    boolean remove(Decoration decoration);

    Decoration findByType(String type);
}
