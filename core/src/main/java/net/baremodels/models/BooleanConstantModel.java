package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.Map;

final class BooleanConstantModel
    implements Model
{

    final boolean value;
    final ModelFactory modelFactory;
    private static BooleanConstantModel TRUE;
    private static BooleanConstantModel FALSE;

    public static BooleanConstantModel of(boolean value, ModelFactory modelFactory) {
        if (value && TRUE!=null) {
            return TRUE;
        }
        if (!value && FALSE!=null) {
            return FALSE;
        }
        if (value) {
            TRUE = new BooleanConstantModel(true,modelFactory);
            return TRUE;
        }
        FALSE = new BooleanConstantModel(false,modelFactory);
        return FALSE;
    }

    private BooleanConstantModel(boolean value, ModelFactory modelFactory) {
        this.value = value;
        this.modelFactory = modelFactory;
    }

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
        Property nameProperty = new StringConstantProperty(NAME,Boolean.toString(value),modelFactory);
        return Collections.singletonMap(NAME, nameProperty);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BooleanConstantModel)) {
            return false;
        }
        BooleanConstantModel that = (BooleanConstantModel) object;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
