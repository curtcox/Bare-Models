package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

public final class SimpleRunner
   implements Runner
{
    private final GenericDevice driver;
    private final ModelRenderer modelRenderer;

    public SimpleRunner(ModelRenderer modelRenderer, GenericDevice driver) {
        this.driver = driver;
        this.modelRenderer = modelRenderer;
    }

    @Override
    public void setModel(Model model) {
        UIComponent ui = modelRenderer.render(model);
        driver.display(ui);
    }

}
