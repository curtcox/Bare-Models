package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.device.SyncDevice;
import net.baremodels.model.Model;
import net.baremodels.ui.UIContainer;

/**
 * A simple implementation of the SyncRunner interface.
 * This class is meant to be extended by supplying the constructor arguments relevant to a particular
 * implementation.
 */
public class SimpleSyncRunner
   implements SyncRunner
{
    private volatile UIContainer renderedModel;
    private volatile NavigationContext navigationContext;
    private final AppContext appContext;
    private final SyncDevice device;
    private final ModelRenderer modelRenderer;
    private final Model.Listener modelListener;
    private final ModelAnalyzer modelAnalyzer;

    public SimpleSyncRunner(AppContext appContext, NavigationContext navigationContext, SyncDevice device, Model.Listener modelListener)
    {
        this(appContext,navigationContext,
             new SimpleModelRenderer(new SimplePropertyNameMapper(),appContext),
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
        renderedModel = modelRenderer.render(current);
        Model selected = device.display(renderedModel,appContext.layout(renderedModel,device.getDeviceState()));
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
        if (renderedModel !=null) {
            redisplay(state);
        }
    }

    private void redisplay(DeviceState state) {
        device.redisplay(renderedModel, appContext.layout(renderedModel, state));
    }

    @Override
    public void onChange(AppContext context) {
        if (renderedModel !=null) {
            redisplay(device.getDeviceState());
        }
    }
}
