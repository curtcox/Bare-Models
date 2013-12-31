package net.baremodels.runner;

import net.baremodels.device.DeviceState;
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
    private volatile UIContainer container;
    private volatile NavigationContext navigationContext;
    private final AppContext appContext;
    private final SyncDevice device;
    private final ModelRenderer modelRenderer;
    private final Model.Listener modelListener;
    private final ModelAnalyzer modelAnalyzer;

    public SimpleSyncRunner(AppContext appContext, NavigationContext navigationContext, SyncDevice device, Model.Listener modelListener)
    {
        this(appContext,navigationContext,
             new SimpleModelRenderer(new SimplePropertyNameMapper(),new SimplePropertyIconMapper()),
             device,modelListener,new SimpleModelAnalyzer());
    }

    SimpleSyncRunner(AppContext appContext, NavigationContext navigationContext,
        ModelRenderer modelRenderer, SyncDevice device,
        Model.Listener modelListener, ModelAnalyzer modelAnalyzer)
    {
        this.appContext = appContext;
        this.navigationContext = navigationContext;
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.modelListener = modelListener;
        this.modelAnalyzer = modelAnalyzer;
    }

    @Override
    final public Model display(Model current) {
        container = modelRenderer.render(current,navigationContext);
        Model selected = device.display(container,appContext.layout(container,device.getDeviceState()));
        if (selected==current) {
            return current;
        }
        modelListener.onChange(selected);
        if (!modelAnalyzer.generatesSingleIntent(selected)) {
            return selected;
        }
        generateIntent(selected);
        return current;
    }

    private void generateIntent(Model model) {
        device.onIntent(modelAnalyzer.generateIntent(model));
    }

    @Override
    public void onChange(DeviceState state) {
        if (container!=null) {
            redisplay(state);
        }
    }

    private void redisplay(DeviceState state) {
        device.redisplay(container, appContext.layout(container, state));
    }

    @Override
    public void onChange(AppContext context) {
        if (container!=null) {
            redisplay(device.getDeviceState());
        }
    }
}
