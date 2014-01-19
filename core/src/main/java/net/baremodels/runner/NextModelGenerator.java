package net.baremodels.runner;

import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;

/**
 * For generating the next Model from the current and selected ones.
 * This is used to support two basic cases:
 * <ul>
 *     <li> model where selection replaces the model</li>
 *     <li> models where selection mutates the state of the model</li>
 * </ul>
 */
public interface NextModelGenerator {
    Model generateNextModel(Model current, Inspectable selected);
}
