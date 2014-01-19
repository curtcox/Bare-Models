package net.baremodels.ui;

import net.baremodels.model.Inspectable;

/**
 * A user interface component.
 */
public interface UIComponent {

    /**
     * The domain concept that this component contains.
     * It can be a Model, Property, or Operation.
     * <ul>
     *   <li>
     *     <b>Model</b> -- this is <b>not</b> a UI component model of the same type you would use in
     *     Swing or a similar MVC toolkit.  Rather, it is the Model that will be transitioned to if this component
     *     is selected.
     *   </li>
     *   <li>
     *     <b>Property</b> -- what this component can be used to view or edit.
     *   </li>
     *   <li>
     *     <b>Operation</b> -- what will happen if this operation is selected.
     *   </li>
     * </ul>
     */
    Inspectable getInspectable();

    /**
     * The name of this component.
     */
    String getName();

    /**
     * For listening to UI selection.
     */
    interface Listener {
        void onSelected(Inspectable inspectable);
    }

    /**
     * For determining if a given component meets certain criteria.
     */
    interface Matcher {
        boolean matches(UIComponent component);
    }
}
