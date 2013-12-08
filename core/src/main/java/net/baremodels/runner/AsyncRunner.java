package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

/**
 * Asynchronously displays selectable models on a device.
 */
public class AsyncRunner
    implements UIComponent.Listener
{

    private Model current;
    private final AsyncDevice device;
    private final ModelRenderer modelRenderer;
    private final ModelAnalyzer modelAnalyzer;

    public AsyncRunner(ModelRenderer modelRenderer, AsyncDevice device) {
        this(modelRenderer, new SimpleModelAnalyzer(),device);
    }

    AsyncRunner(ModelRenderer modelRenderer, ModelAnalyzer modelAnalyzer, AsyncDevice device) {
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.modelAnalyzer = modelAnalyzer;
    }

    /**
     * Display the given model on the device.
     */
    final public void display(Model model) {
        current = model;
        device.display(modelRenderer.render(model, null));
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

}
