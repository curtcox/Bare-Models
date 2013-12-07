package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.intent.Intent;
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

    public void display(Model model) {
        current = model;
        device.display(modelRenderer.render(model, null));
    }

    private void generateIntent(Model model) {
        Object result = modelAnalyzer.invokeOperation(model);
        device.onIntent((Intent)result);
    }

    @Override
    public void onSelected(Model model) {
        if (model==current) {
            return;
        }
        if (!modelAnalyzer.generatesSingleIntent(model)) {
            display(model);
            return;
        }
        generateIntent(model);
    }
}
