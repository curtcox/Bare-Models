package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.device.SyncDevice;
import net.baremodels.model.Inspectable;
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
    private final NextModelGenerator generator;
    private final AppContext appContext;
    private final SyncDevice device;
    private final ModelContainerRenderer modelRenderer;
    private final Model.Listener modelListener;
    private final ModelAnalyzer modelAnalyzer;

    public SimpleSyncRunner(AppContext appContext,
        SyncDevice device, NextModelGenerator generator,
        Model.Listener modelListener)
    {
        this(appContext,
             new SimpleModelContainerRenderer(new SimplePropertyNameMapper(),appContext),
             device,generator,modelListener,new SimpleModelAnalyzer());
    }

    public SimpleSyncRunner(AppContext appContext,
        ModelContainerRenderer modelRenderer,SyncDevice device, NextModelGenerator generator,
        Model.Listener modelListener)
    {
        this(appContext, modelRenderer, device, generator,modelListener,new SimpleModelAnalyzer());
    }

    SimpleSyncRunner(AppContext appContext,
        ModelContainerRenderer modelRenderer, SyncDevice device, NextModelGenerator generator,
        Model.Listener modelListener, ModelAnalyzer modelAnalyzer)
    {
        this.appContext = appContext;
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.generator = generator;
        this.modelListener = modelListener;
        this.modelAnalyzer = modelAnalyzer;
    }

    @Override
    public Model generateNextModel(Model current, Inspectable selected) {
        return generator.generateNextModel(current,selected);
    }

    @Override
    final public Inspectable display(Model current) {
        renderedModel = modelRenderer.render(current);
        Inspectable selected = device.display(renderedModel,appContext.layout(renderedModel,device.getDeviceState()));
        if (selected==current) {
            return current;
        }
        modelListener.onChange((Model) selected);
        if (!modelAnalyzer.generatesSingleIntent(selected)) {
            return selected;
        }
        generateIntent(selected);
        return current;
    }

    private void generateIntent(Inspectable inspectable) {
        device.onIntent(modelAnalyzer.generateIntent(inspectable));
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
