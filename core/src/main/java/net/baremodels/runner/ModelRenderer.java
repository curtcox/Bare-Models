package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

/**
 * For rendering ModelS into UIComponentS.
 */
public interface ModelRenderer {

    /**
     * Given a Model, render it into a UIComponent.
     */
    UIComponent render(Model model);
}
