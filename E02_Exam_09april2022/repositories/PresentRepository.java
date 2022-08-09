package fairyShop.repositories;

import fairyShop.models.presents.Present;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentRepository implements Repository<Present>{
    private Map<String, Present> presents;

    public PresentRepository(){
        this.presents = new LinkedHashMap<>();
    }

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(this.presents.values());
    }

    @Override
    public void add(Present model) {
        this.presents.put(model.getName(), model);
    }

    @Override
    public boolean remove(Present model) {
        return this.presents.remove(model.getName()) != null;
    }

    @Override
    public Present findByName(String name) {
        return this.presents.get(name);
    }
}
