package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

public interface ModelRenderer {
    UIComponent render(Model model);
}
