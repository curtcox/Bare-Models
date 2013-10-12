package net.baremodels.models;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class NullModelTest {

    @Test
    public void there_is_only_one_NullModel() {
        assertSame(NullModel.of(),NullModel.of());
    }
}
