package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.model.Model;
import org.junit.Test;

public class SimpleRunnerTest {

    ModelRenderer modelRenderer;
    GenericDevice driver;
    Model model;

    SimpleRunner testObject = new SimpleRunner(modelRenderer,driver);

    @Test
    public void model() {
        testObject.setModel(model);
    }
}
