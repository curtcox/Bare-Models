package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.device.DeviceState;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;

import static org.junit.Assert.*;
import static test.mock.Mocks.*;

public class AsyncRunnerTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model initial = modelFactory.of("initial");
    Model selected = modelFactory.of("selected");

    ModelRenderer modelRenderer;
    ModelAnalyzer modelAnalyzer;
    AppContext appContext;

    UILayout layout = new UILayout(new HashMap<>());
    Intent intent;
    UIContainer displayed;
    AsyncDevice device;
    DeviceState deviceState = new DeviceState(19,69);

    AsyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);

        _(deviceState); device.getDeviceState();
        _();            device.display(displayed,layout);
        _();            device.onIntent(intent);
        _(displayed);   modelRenderer.render(initial);
        _(displayed);   modelRenderer.render(selected);
        _(layout);      appContext.layout(displayed,deviceState);
        _(false);       modelAnalyzer.generatesSingleIntent(selected);

        testObject = new AsyncRunner(appContext, modelRenderer, modelAnalyzer, device);
    }

    @Test
    public void is_UIComponent_Listener() {
        assertTrue(testObject instanceof UIComponent.Listener);
    }

    @Test
    public void is_DeviceState_Listener() {
        assertTrue(testObject instanceof DeviceState.Listener);
    }

    @Test
    public void onSelected_displays_on_device_when_changed_selection() {
        no();  device.onIntent(intent);

        testObject.display(initial);
        testObject.onSelected(selected);

        verify();
        device.display(displayed,layout);
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
        device.display(displayed,layout);
    }

    @Test
    public void onChange_displays_rendered_model_on_device_using_new_device_state() {
        selected = initial;
        testObject.display(initial);

        DeviceState newState = new DeviceState(18,12);
        UILayout   newLayout = new UILayout(new HashMap<>());
        _(newLayout);  appContext.layout(displayed,newState);
        _();           device.display(displayed,newLayout);

        testObject.onChange(newState);

        verify();
        device.display(displayed,newLayout);
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
