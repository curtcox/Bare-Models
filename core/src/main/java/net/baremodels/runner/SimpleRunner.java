package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
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
    private final GenericDevice driver;
    private final ModelRenderer modelRenderer;
    private final Model.Listener listener;

    public SimpleRunner(ModelRenderer modelRenderer, GenericDevice driver) {
        this(modelRenderer,driver,x -> {});
    }

    public SimpleRunner(ModelRenderer modelRenderer, GenericDevice driver, Model.Listener listener) {
        this.driver = driver;
        this.modelRenderer = modelRenderer;
        this.listener = listener;
    }

    /**
     * Iteratively display rendered selectable, using the driver, until the exit condition has been met.
     * A listener is notified every time a new model is selected.
     */
    @Override
    final public void setModel(Model model, Predicate<Model> keepGoing) {
        while (keepGoing.test(model)) {
            System.out.println("Displaying " + model);
            UIComponent ui = modelRenderer.render(model);
            model = driver.display(ui);
            listener.onChange(model);
        }
    }
}
