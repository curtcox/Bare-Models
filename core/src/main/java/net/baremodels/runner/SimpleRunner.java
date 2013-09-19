package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

import java.util.function.Predicate;

/**
 * A simple implementation of the Runner interface.
 */
public final class SimpleRunner
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
     * Iteratively display rendered models, using the driver, until the exit condition has been met.
     * A listener is notified every time a new model is selected.
     */
    @Override
    public void setModel(Model model, Predicate<Model> keepGoing) {
        while (keepGoing.test(model)) {
            System.out.println("Displaying " + model);
            UIComponent ui = modelRenderer.render(model);
            Model selected = driver.display(ui);
            System.out.println("Selected 3 " + selected);
            model = selected;
            listener.onChange(model);
        }
    }
}
