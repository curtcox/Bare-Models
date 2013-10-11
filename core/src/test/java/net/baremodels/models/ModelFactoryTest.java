package net.baremodels.models;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ModelFactoryTest {

    @Test
    public void default_ModelFactory_is_ModelFactory_from_ObjectModel() {
        assertSame(ModelFactory.DEFAULT,ObjectModel.FACTORY);
    }
}
