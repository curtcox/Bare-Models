package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.device.DeviceState;
import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

/**
 * Asynchronously displays selectable models on a device.
 */
public class AsyncRunner
    implements UIComponent.Listener, DeviceState.Listener
{

    private Model current;
    private NavigationContext navigationContext;
    private UIContainer container;

    private final AppContext appContext;
    private final AsyncDevice device;
    private final ModelRenderer modelRenderer;
    private final ModelAnalyzer modelAnalyzer;

    public AsyncRunner(AppContext appContext, NavigationContext navigationContext,
        ModelRenderer modelRenderer, AsyncDevice device) {
        this(appContext, navigationContext, modelRenderer, new SimpleModelAnalyzer(),device);
    }

    AsyncRunner(AppContext appContext, NavigationContext navigationContext,
        ModelRenderer modelRenderer, ModelAnalyzer modelAnalyzer, AsyncDevice device) {
        this.appContext = appContext;
        this.navigationContext = navigationContext;
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
     * Note the selection of a model.
     * If the model is different than the current one, this will cause the display to be updated.
     * If the model generates a single intent, then that intent will be relayed to the device.
     */
    @Override
    final public void onSelected(Model model) {
        if (model==current) {
            return;
        }
        if (!modelAnalyzer.generatesSingleIntent(model)) {
            display(model);
            return;
        }
        generateIntent(model);
    }

    private void generateIntent(Model model) {
        device.onIntent(modelAnalyzer.generateIntent(model));
    }

    @Override
    public void onChange(DeviceState state) {
        device.display(container,appContext.layout(container,state));
    }
}
