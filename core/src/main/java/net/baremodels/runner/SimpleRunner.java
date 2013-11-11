package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

import java.util.function.Predicate;

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

    /**
     * Iteratively display rendered selectable, using the device, until the exit condition has been met.
     * A listener is notified every time a new model is selected.
     */
    @Override
    final public void setModel(Model model, Predicate<Model> keepGoing) {
        while (keepGoing.test(model)) {
            System.out.println("Displaying " + model);
            UIComponent ui = modelRenderer.render(model);
            Model selected = device.display(ui);
            listener.onChange(selected);
            if (generatesSingleIntent(selected)) {
                generateIntent(selected);
            } else {
                model = selected;
            }
        }
    }

    private void generateIntent(Model model) {
        Object result = model.operations().values().iterator().next().invoke();
        device.onIntent((Intent)result);
    }

    private boolean generatesSingleIntent(Model model) {
        return model.operations().size()==1;
    }
}
