package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

/**
 * A simple implementation of the Runner interface.
 * This class is meant to be extended by supplying the constructor arguments relevant to a particular
 * implementation.
 */
public class SimpleRunner
   implements Runner
{
    private final GenericDevice device;
    private final ModelRenderer modelRenderer;
    private final Model.Listener listener;
    private final ModelAnalyzer modelAnalyzer;

    /**
     * @param modelRenderer for Model to UI
     * @param device to display the UI to the user
     * @param listener listen to any user selections
     */
    public SimpleRunner(ModelRenderer modelRenderer, GenericDevice device, Model.Listener listener) {
        this(modelRenderer,device,listener, new SimpleModelAnalyzer());
    }

    SimpleRunner(ModelRenderer modelRenderer, GenericDevice device, Model.Listener listener, ModelAnalyzer modelAnalyzer) {
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.listener = listener;
        this.modelAnalyzer = modelAnalyzer;
    }

    @Override
    final public Model display(Model current) {
        UIComponent ui = modelRenderer.render(current,null);
        Model selected = device.display(ui);
        if (selected==current) {
            return current;
        }
        listener.onChange(selected);
        if (!modelAnalyzer.generatesSingleIntent(selected)) {
            return selected;
        }
        device.onIntent(modelAnalyzer.generateIntent(selected));
        return current;
    }

}
