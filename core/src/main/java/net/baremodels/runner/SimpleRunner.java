package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.model.Model;

public final class SimpleRunner
   implements Runner
{
    private final GenericDevice driver;
    private final ModelRenderer modelRenderer;

    SimpleRunner(ModelRenderer modelRenderer, GenericDevice driver) {
        this.driver = driver;
        this.modelRenderer = modelRenderer;
    }

    @Override
    public void setModel(Model model) {
        UIComponent ui = modelRenderer.render(model);
        driver.display(ui);
    }

}
