package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIComponent;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static test.mock.Mocks.*;

public class AsyncRunnerTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model initial = modelFactory.of("initial");
    Model selected = modelFactory.of("selected");

    ModelRenderer modelRenderer;
    ModelAnalyzer modelAnalyzer;

    Intent intent;
    UIComponent displayed;
    AsyncDevice device;

    AsyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        _();          device.display(displayed);
        _();          device.onIntent(intent);
        _(displayed); modelRenderer.render(initial, null);
        _(displayed); modelRenderer.render(selected, null);
        _(false);     modelAnalyzer.generatesSingleIntent(selected);
        testObject = new AsyncRunner(modelRenderer, modelAnalyzer, device);
    }

    @Test
    public void onSelected_displays_on_device_when_changed_selection() {
        no();  device.onIntent(intent);

        testObject.display(initial);
        testObject.onSelected(selected);

        verify();
        device.display(displayed);
    }

    @Test
    public void display_does_not_notify_model_listener_on_unchanged_selection() {
        selected = initial;
        no();  device.onIntent(intent);

        testObject.display(initial);
    }

    @Test
    public void display_displays_rendered_model_on_device() {
        selected = initial;

        testObject.display(initial);

        verify();
        device.display(displayed);
    }

    @Test
    public void onSelected_notifies_device_of_intent_when_model_generates_single_intent() {
        _(true);   modelAnalyzer.generatesSingleIntent(selected);
        _(intent); modelAnalyzer.generateIntent(selected);

        testObject.onSelected(selected);

        verify();
        device.onIntent(intent);
    }

}
