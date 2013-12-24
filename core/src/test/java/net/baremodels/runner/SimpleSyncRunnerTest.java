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

import static org.junit.Assert.assertSame;
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
    DeviceState deviceState = new DeviceState();

    SimpleSyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        _(layout);      appContext.layout(container,deviceState);
        _(container);   modelRenderer.render(initial,navigationContext);
        _(selected);    device.display(container,layout);
        _(deviceState); device.getDeviceState();
        _();            device.onIntent(intent);
        _();            listener.onChange(selected);
        _(intent);      modelAnalyzer.generateIntent(selected);
        _(false);       modelAnalyzer.generatesSingleIntent(selected);

        testObject = new SimpleSyncRunner(appContext, navigationContext, modelRenderer, device,listener, modelAnalyzer);
    }

    @Test
    public void display_displays_rendered_ui_on_device_when_selection_changes() {
        testObject.display(initial);

        verify();

        device.display(container,layout);
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

}
