package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIContainer;

/**
 * For rendering ModelS into UIContainerS.
 */
public interface ModelContainerRenderer {

    /**
     * Given a Model, render it into a UIContainer.
     */
    UIContainer render(Model model);
}
