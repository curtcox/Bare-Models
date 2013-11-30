package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertSame;
import static test.mock.Mocks.*;

public class SimpleRunnerTest {

    Model initial;
    Model selected;
    Intent intent = new Intent(null){};
    ModelAnalyzer modelAnalyzer;
    ModelRenderer modelRenderer;
    GenericDevice device;
    Model.Listener listener;

    SimpleRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new SimpleRunner(modelRenderer, device,listener, modelAnalyzer);
    }

    @Test
    public void display_displays_rendered_ui_on_device_when_selection_changes() {
        testObject.display(initial);

        verify();

        device.display(modelRenderer.render(initial, null));
    }

    @Test
    public void display_notifies_model_listener_on_changed_selection() {
        testObject.display(initial);

        verify();

        listener.onChange(device.display(modelRenderer.render(initial, null)));
    }

    @Test
    public void display_returns_model_on_unchanged_selection() {
        when(device.display(modelRenderer.render(initial,null)),initial);
        Model actual = testObject.display(initial);

        assertSame(initial, actual);
    }

    @Test
    public void display_does_not_notify_model_listener_on_unchanged_selection() {
        when(device.display(modelRenderer.render(initial, null)),initial);

        testObject.display(initial);
        no();

        listener.onChange(null);
        device.onIntent(null);
    }

    @Test
    public void display_notifies_device_of_intent_when_model_generates_single_intent() {
        when(modelAnalyzer.generatesSingleIntent(selected),true);

        testObject.display(initial);
        verify();

        device.onIntent(modelAnalyzer.generateIntent(selected));
    }

    @Test
    public void display_returns_given_model_when_selected_model_generates_single_intent() {
        when(modelAnalyzer.generatesSingleIntent(selected),true);

        Model returned = testObject.display(initial);

        assertSame(initial, returned);
    }

    @Test
    public void display_returns_selected_model_when_it_does_not_generate_intent() {
        when(modelAnalyzer.generatesSingleIntent(selected), false);
        when(device.display(modelRenderer.render(initial,null)),selected);

        Model returned = testObject.display(initial);

        assertSame(selected, returned);
    }

}
