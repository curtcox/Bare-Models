package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.Map;

final class NullModel
    implements Model
{
    static final NullModel singleton = new NullModel();

    /**
     * Don't make me.
     */
    private NullModel(){}

    @Override
    public Map<?, Property> properties() {
        return Collections.emptyMap();
    }

    @Override
    public Map<?, Operation> operations() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Property> meta() {
        return Collections.emptyMap();
    }

    public static NullModel of() {
        return singleton;
    }
}
