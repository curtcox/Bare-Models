package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.Map;

final class NullModel
    implements Model
{
    @Override
    public Map<?, Property> properties() {
        return null;
    }

    @Override
    public Map<?, Operation> operations() {
        return null;
    }

    @Override
    public Map<String, Property> meta() {
        return null;
    }

    public static NullModel of() {
        return new NullModel();
    }
}
