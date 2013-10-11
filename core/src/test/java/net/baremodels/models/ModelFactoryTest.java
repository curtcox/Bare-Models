package net.baremodels.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModelFactoryTest {

    @Test
    public void default_ModelFactory_is_an_ObjectModelFactory() {
        assertTrue(ModelFactory.DEFAULT instanceof ObjectModelFactory);
    }
}
