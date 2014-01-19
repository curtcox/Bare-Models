package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.device.DeviceState;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

/**
 * Asynchronously displays selectable models on a device.
 */
public class AsyncRunner
    implements UIComponent.Listener, DeviceState.Listener
{

    private Model current;
    private UIContainer container;

    private final AppContext appContext;
    private final AsyncDevice device;
    private final ModelContainerRenderer modelRenderer;
    private final ModelAnalyzer modelAnalyzer;

    public AsyncRunner(AppContext appContext,
        ModelContainerRenderer modelRenderer, AsyncDevice device) {
        this(appContext, modelRenderer, new SimpleModelAnalyzer(),device);
    }

    AsyncRunner(AppContext appContext,
        ModelContainerRenderer modelRenderer, ModelAnalyzer modelAnalyzer, AsyncDevice device) {
        this.appContext = appContext;
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.modelAnalyzer = modelAnalyzer;
    }

    /**
     * Display the given model on the device.
     */
    final public void display(Model model) {
        current = model;
        container = modelRenderer.render(model);
        device.display(container,appContext.layout(container,device.getDeviceState()));
    }

    /**
     * Note the selection of an inspectable.
     * If it is different than the current one, this will cause the display to be updated.
     * If the model generates a single intent, then that intent will be relayed to the device.
     */
    @Override
    final public void onSelected(Inspectable selection) {
        if (selection==current) {
            return;
        }
        if (!modelAnalyzer.generatesSingleIntent(selection)) {
            display((Model) selection);
            return;
        }
        generateIntent(selection);
    }

    private void generateIntent(Inspectable inspectable) {
        device.onIntent(modelAnalyzer.generateIntent(inspectable));
    }

    @Override
    public void onChange(DeviceState state) {
        device.display(container,appContext.layout(container,state));
    }
}
