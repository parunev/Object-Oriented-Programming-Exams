package fairyShop.repositories;

import fairyShop.models.helper.Helper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelperRepository implements Repository<Helper>{

    private Map<String, Helper> helpers;

    public HelperRepository() {
        this.helpers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(this.helpers.values());
    }

    @Override
    public void add(Helper model) {
        this.helpers.put(model.getName(), model);
    }

    @Override
    public boolean remove(Helper model) {
        return this.helpers.remove(model.getName()) != null;
    }

    @Override
    public Helper findByName(String name) {
        return this.helpers.get(name);
    }
}
