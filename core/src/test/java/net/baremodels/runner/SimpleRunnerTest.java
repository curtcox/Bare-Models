package net.baremodels.runner;

import net.baremodels.apps.Nucleus;
import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

public class SimpleRunnerTest {

    ModelRenderer modelRenderer = new SimpleModelRenderer();
    GenericDevice driver = new GenericDevice() {

        @Override
        public UIComponent display(UIComponent ui) {
            throw new IllegalArgumentException(ui.toString());
        }

        @Override
        public void onIntent(Intent intent) {
        }
    };

    Nucleus nucleus = new Nucleus();
    Model model = ObjectModel.of(nucleus);

    SimpleRunner testObject = new SimpleRunner(modelRenderer,driver);

    @Test
    public void model() {
        testObject.setModel(model);
    }
}
