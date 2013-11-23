package net.baremodels.runner;

import net.baremodels.device.vaadin.VaadinDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

/**
 * Spike!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Add tests
 */
public class AsyncRunner
    implements UIComponent.Listener
{

    private Model current;
    private final VaadinDevice device;
    private final ModelRenderer modelRenderer;

    public AsyncRunner(ModelRenderer modelRenderer, VaadinDevice device) {
        this.device = device;
        this.modelRenderer = modelRenderer;
    }

    public void display(Model model) {
        current = model;
        device.display(modelRenderer.render(model, null));
    }

    private void generateIntent(Model model) {
        Object result = invokeOperation(model);
        device.onIntent((Intent)result);
    }

    private Object invokeOperation(Model model) {
        return model.operations().values().iterator().next().invoke();
    }

    private boolean generatesSingleIntent(Model model) {
        return model.operations().size()==1 && (invokeOperation(model) instanceof Intent);
    }

    @Override
    public void onSelected(Model model) {
        System.out.println("---------- async runner : " + model);
        if (model==current) {
            return;
        }
        if (!generatesSingleIntent(model)) {
            display(model);
            return;
        }
        generateIntent(model);
    }
}
