package net.baremodels.runner;

import net.baremodels.model.Model;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertSame;

public class SelectedNextModelGeneratorTest {

    Model current;
    Model selected;

    @Before
    public void init() {
        Mocks.init(this);
    }

    SelectedNextModelGenerator testObject = new SelectedNextModelGenerator();

    @Test
    public void generateNextModel_returns_selected_model() {
        Model expected = selected;

        Model actual = testObject.generateNextModel(current,selected);

        assertSame(expected,actual);
    }
}
