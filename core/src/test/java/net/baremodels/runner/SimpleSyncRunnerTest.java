package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.device.SyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;

import static org.junit.Assert.*;
import static test.mock.Mocks.*;

public class SimpleSyncRunnerTest {

    Model initial;
    Model selected;
    UIContainer container;
    UILayout layout = new UILayout(new HashMap<>());
    Intent intent = new Intent(null){};
    ModelAnalyzer modelAnalyzer;
    ModelRenderer modelRenderer;
    SyncDevice device;
    Model.Listener listener;
    AppContext appContext;
    NavigationContext navigationContext = new NavigationContext();
    DeviceState deviceState = new DeviceState(17,83);

    SimpleSyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        _(layout);      appContext.layout(container, deviceState);
        _(container);   modelRenderer.render(initial);
        _(selected);    device.display(container,layout);
        _(deviceState); device.getDeviceState();
        _();            device.onIntent(intent);
        _();            listener.onChange(selected);
        _(intent);      modelAnalyzer.generateIntent(selected);
        _(false);       modelAnalyzer.generatesSingleIntent(selected);

        testObject = new SimpleSyncRunner(appContext, navigationContext, modelRenderer, device,listener, modelAnalyzer);
    }

    @Test
    public void is_a_SyncRunner() {
        assertTrue(testObject instanceof SyncRunner);
    }

    @Test
    public void is_a_DeviceState_Listener() {
        assertTrue(testObject instanceof DeviceState.Listener);
    }

    @Test
    public void is_an_AppContext_Listener() {
        assertTrue(testObject instanceof AppContext.Listener);
    }

    @Test
    public void display_displays_rendered_ui_on_device_when_selection_changes() {
        testObject.display(initial);

        verify();

        device.display(container,layout);
    }

    @Test
    public void display_redisplays_rendered_ui_on_device_when_device_state_changes() {
        testObject.display(initial);

        DeviceState newState = new DeviceState(17,76);
        UILayout newLayout = new UILayout(new HashMap<>());
        _(newLayout); appContext.layout(container,newState);
        _();          device.redisplay(container, newLayout);

        testObject.onChange(newState);

        verify();

        device.redisplay(container, newLayout);
    }

    @Test
    public void display_redisplays_rendered_ui_on_device_when_app_context_state_changes() {
        testObject.display(initial);

        UILayout newLayout = new UILayout(new HashMap<>());
        _(newLayout); appContext.layout(container,deviceState);
        _();          device.redisplay(container, newLayout);

        testObject.onChange(appContext);

        verify();

        device.redisplay(container, newLayout);
    }

    @Test
    public void display_notifies_model_listener_on_changed_selection() {
        testObject.display(initial);

        verify();

        listener.onChange(selected);
    }

    @Test
    public void display_returns_model_on_unchanged_selection() {
        _(initial); device.display(container,layout);

        Model actual = testObject.display(initial);

        assertSame(initial, actual);
    }

    @Test
    public void display_does_not_notify_model_listener_on_unchanged_selection() {
        _(initial); device.display(container,layout);

        no(); listener.onChange(null);
        no(); device.onIntent(null);

        testObject.display(initial);
    }

    @Test
    public void display_notifies_device_of_intent_when_model_generates_single_intent() {
        _(true); modelAnalyzer.generatesSingleIntent(selected);

        testObject.display(initial);

        verify();
        device.onIntent(modelAnalyzer.generateIntent(selected));
    }

    @Test
    public void display_returns_given_model_when_selected_model_generates_single_intent() {
        _(true); modelAnalyzer.generatesSingleIntent(selected);

        Model returned = testObject.display(initial);

        assertSame(initial, returned);
    }

    @Test
    public void display_returns_selected_model_when_it_does_not_generate_intent() {
        _(false); modelAnalyzer.generatesSingleIntent(selected);

        Model returned = testObject.display(initial);

        assertSame(selected, returned);
    }

    @Test
    public void onChange_DeviceState_does_nothing_when_display_has_not_been_called() {
        no(); device.redisplay(null,layout);
        no(); appContext.layout(null, deviceState);

        testObject.onChange(deviceState);
    }

    @Test
    public void onChange_AppContext_does_nothing_when_display_has_not_been_called() {
        no(); device.redisplay(null,layout);
        no(); appContext.layout(null, deviceState);

        testObject.onChange(appContext);
    }

}
