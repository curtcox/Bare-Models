package net.baremodels.ui;

import net.baremodels.model.Model;

/**
 * A user interface component.
 */
public interface UIComponent {

    /**
     * The model of the domain object that this component contains.
     * This is <b>not</b> a UI component model of the same type you would use in
     * Swing or a similar MVC toolkit.
     */
    Model getModel();

    /**
     * The name of this component.
     */
    String getName();

    /**
     * For listening to UI selection.
     */
    interface Listener {
        void onSelected(Model model);
    }
}
