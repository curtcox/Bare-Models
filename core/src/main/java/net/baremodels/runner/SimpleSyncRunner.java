package net.baremodels.runner;

import net.baremodels.device.SyncDevice;
import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.ui.UIContainer;

/**
 * A simple implementation of the SyncRunner interface.
 * This class is meant to be extended by supplying the constructor arguments relevant to a particular
 * implementation.
 */
public class SimpleSyncRunner
   implements SyncRunner
{
    private NavigationContext navigationContext;
    private final AppContext appContext;
    private final SyncDevice device;
    private final ModelRenderer modelRenderer;
    private final Model.Listener listener;
    private final ModelAnalyzer modelAnalyzer;

    /**
     * @param modelRenderer for Model to UI
     * @param device to display the UI to the user
     * @param listener listen to any user selections
     */
    public SimpleSyncRunner(AppContext appContext, ModelRenderer modelRenderer, SyncDevice device, Model.Listener listener) {
        this(appContext,modelRenderer,device,listener, new SimpleModelAnalyzer());
    }

    SimpleSyncRunner(AppContext appContext, ModelRenderer modelRenderer, SyncDevice device, Model.Listener listener, ModelAnalyzer modelAnalyzer) {
        this.appContext = appContext;
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.listener = listener;
        this.modelAnalyzer = modelAnalyzer;
    }

    @Override
    final public Model display(Model current) {
        UIContainer ui = modelRenderer.render(current,navigationContext);
        Model selected = device.display(ui,appContext.layout(ui,device.getDeviceState()));
        if (selected==current) {
            return current;
        }
        listener.onChange(selected);
        if (!modelAnalyzer.generatesSingleIntent(selected)) {
            return selected;
        }
        generateIntent(selected);
        return current;
    }

    private void generateIntent(Model model) {
        device.onIntent(modelAnalyzer.generateIntent(model));
    }

}
