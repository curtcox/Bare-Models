package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.Map;

final class StringConstantModel
    implements Model
{

    final String value;
    final ModelFactory modelFactory;

    StringConstantModel(String value, ModelFactory modelFactory) {
        this.value = value;
        this.modelFactory = modelFactory;
    }

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
        Property nameProperty = new StringConstantProperty(NAME,value,modelFactory);
        return Collections.singletonMap(NAME, nameProperty);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StringConstantModel)) {
            return false;
        }
        StringConstantModel that = (StringConstantModel) object;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
