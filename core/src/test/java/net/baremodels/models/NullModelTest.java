package net.baremodels.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NullModelTest {

    @Test
    public void there_is_only_one_NullModel() {
        assertSame(NullModel.of(),NullModel.of());
    }

    @Test
    public void has_no_operations() {
        assertTrue(NullModel.of().operations().isEmpty());
    }

    @Test
    public void has_no_properties() {
        assertTrue(NullModel.of().properties().isEmpty());
    }

    @Test
    public void has_no_meta() {
        assertTrue(NullModel.of().meta().isEmpty());
    }
}
