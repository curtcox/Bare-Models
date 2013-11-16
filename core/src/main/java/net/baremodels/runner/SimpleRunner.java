package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
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

    /**
     * Create a SimpleRunner that will run forever.
     */
    public SimpleRunner(ModelRenderer modelRenderer, GenericDevice driver) {
        this(modelRenderer,driver,x -> {});
    }

    public SimpleRunner(ModelRenderer modelRenderer, GenericDevice device, Model.Listener listener) {
        this.device = device;
        this.modelRenderer = modelRenderer;
        this.listener = listener;
    }

    @Override
    final public Model display(Model current) {
        System.out.println("Displaying " + current);
        UIComponent ui = modelRenderer.render(current);
        Model selected = device.display(ui);
        if (selected==current) {
            return current;
        }
        listener.onChange(selected);
        if (!generatesSingleIntent(selected)) {
            return selected;
        }
        generateIntent(selected);
        return current;
    }

    private void generateIntent(Model model) {
        Object result = model.operations().values().iterator().next().invoke();
        device.onIntent((Intent)result);
    }

    private boolean generatesSingleIntent(Model model) {
        return model.operations().size()==1;
    }
}
