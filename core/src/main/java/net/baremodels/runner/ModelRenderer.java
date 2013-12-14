package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.model.ModelContext;
import net.baremodels.ui.UIContainer;

/**
 * For rendering ModelS into UIComponentS.
 */
public interface ModelRenderer {

    /**
     * Given a Model, render it into a UIComponent.
     */
    UIContainer render(Model model, ModelContext context);
}
